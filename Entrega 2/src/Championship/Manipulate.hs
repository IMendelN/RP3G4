module Championship.Manipulate where

import Data.List ( sortOn )
import Data.Ord ( Down (Down) )

import qualified Championship.ReadFile as File ( readDatabase, splitBy )
import Championship.Structures as Struct
import Utils.Utils as U

--
-- Declaração de sinônimos para facilitar a leitura
-- de alguns métodos.
--
type Round = Integer
type Team = String
type Goals = Integer
type Winner = String
type Wins = Integer
type Draws = Integer
type Losses = Integer

--
-- Transforma uma lista de String em uma "struct" de partida.
--
parseToMatch :: [String] -> [Match]
parseToMatch [] = []
parseToMatch fileLine = map (parseLine . File.splitBy ';') fileLine
    where
        parseLine line = Match (read (head line)) (line !! 1)
                               (read (line !! 2)) (read (line !! 3))
                               (line !! 4)

--
-- Retorna todas as partidas do arquivo de banco de dados
-- já no formato de "struct" de partida.
--
getMatches :: IO [Match]
getMatches = do
    fileContent <- File.readDatabase
    let matches = parseToMatch fileContent
    return matches

--
-- Filtra as partidas passando a rodada.
--
filterByRound :: Round -> Team -> [Match] -> [Match]
filterByRound round team [] = []
filterByRound round team matches = do
    filter (\match -> _round match == round 
        && (homeTeam match == team || awayTeam match == team)) matches

--
-- Filtra as partidas passando o nome do time.
--
filterByTeam :: Team -> [Match] -> [Match]
filterByTeam team [] = []
filterByTeam team matches = do
    filter (\match -> homeTeam match == team || awayTeam match == team) matches

--
-- Retorna uma "instância" de uma partida passando a rodada
-- e o nome do time (RF5).
--
getResultByRoundAndTeam :: Round -> Team -> [Match] -> Match
getResultByRoundAndTeam _ _ [] = Match 0 "Partida." 0 0 "Inválida."
getResultByRoundAndTeam round team matches = do
    let filtered = filterByRound round team matches
    let check | null filtered = getResultByRoundAndTeam 0 "" []
              | otherwise = head filtered
    check

--
-- Imprime o nome do time vencedor de uma partida específica ou
-- mostra se a mesma obteve um empate.
--
getWinnerByRoundAndTeam :: Match -> Winner
getWinnerByRoundAndTeam match = do
    let msg = U.green ++ "> O vencedor da partida: "
    let check | goalsHomeTeam match > goalsAwayTeam match = msg ++ homeTeam match ++ U.reset
              | goalsAwayTeam match > goalsHomeTeam match = msg ++ awayTeam match ++ U.reset
              | otherwise = U.cyan ++ "> Partida empatada." ++ U.reset
    check

--
-- Imprime o resultado de uma partida específica (RF5).
--
showResultByRoundAndTeam :: Match -> IO ()
showResultByRoundAndTeam match = do
    putStrLn $ U.purple ++ "\n[RESULTADO DA PARTIDA]\n" ++ U.reset
    putStrLn "+----------------------------------------+"
    putStr $ "  " ++ U.blue ++ homeTeam match ++ " | " ++ show (goalsHomeTeam match) ++ " x "
    putStrLn $ show (goalsAwayTeam match) ++ " | " ++ awayTeam match ++ U.reset
    putStrLn "+----------------------------------------+"
    putStrLn $ getWinnerByRoundAndTeam match ++ U.reset

--
-- Ordena o resultado das partidas em ordem decrescente.
-- Assim, deixando transparente a classificação do campeonato.
--
sortMatches :: [MatchResult] -> [MatchResult]
sortMatches = sortOn (Down . points)

--
-- Retorna a quantidade de vitórias, empates e derrotas de um determinado time.
--
getTeamPerformance :: Team -> [Match] -> (Wins, Draws, Losses)
getTeamPerformance _ [] = (0, 0, 0)
getTeamPerformance team allMatches = do
    let (match : matches) = filterByTeam team allMatches
    let ht = homeTeam match
    let at = awayTeam match
    let ght = goalsHomeTeam match
    let gat = goalsAwayTeam match
    let check | ht == team && ght > gat                  = updateWins   $ getTeamPerformance team matches
              | ht == team && ght < gat                  = updateLosses $ getTeamPerformance team matches
              | at == team && gat > ght                  = updateWins   $ getTeamPerformance team matches
              | at == team && gat < ght                  = updateLosses $ getTeamPerformance team matches
              | (ht == team || at == team) && gat == ght = updateDraws  $ getTeamPerformance team matches
              | otherwise = getTeamPerformance team matches
    check

--
-- Atualiza as vitórias de um time.
--
updateWins :: (Wins, Draws, Losses) -> (Wins, Draws, Losses)
updateWins (wins, draws, losses) = (wins + 1, draws, losses)

--
-- Atualiza as derrotas de um time.
--
updateLosses :: (Wins, Draws, Losses) -> (Wins, Draws, Losses)
updateLosses (wins, draws, losses) = (wins, draws, losses + 1)

--
-- Atualiza os empates de um time.
--
updateDraws :: (Wins, Draws, Losses) -> (Wins, Draws, Losses)
updateDraws (wins, draws, losses) = (wins, draws + 1, losses)
    
