module Championship.Manipulate where

import Data.List ( sortOn )
import Data.Ord ( Down (Down) )

import qualified Championship.ReadFile as File ( readDatabase, splitBy )
import Championship.Structures as Struct ( TeamResult(points), Match(..) )
import Utils.Utils as U ( green, cyan, reset )

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
type Points = Integer
type Record = Float

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
-- Ordena o resultado das partidas em ordem decrescente.
-- Assim, deixando transparente a classificação do campeonato.
--
sortMatches :: [TeamResult] -> [TeamResult]
sortMatches = sortOn (Down . points)

--
-- Retorna a quantidade de vitórias, empates e derrotas de um determinado time.
--
getTeamPerformance :: Team -> [Match] -> (Wins, Draws, Losses)
getTeamPerformance _ [] = (0, 0, 0)
getTeamPerformance team matches = do
    let filtered = filterByTeam team matches
    let wins = getWinsByTeam team filtered
    let draws = getDrawsByTeam team filtered
    let losses = getLossesByTeam team filtered
    (wins, draws, losses)

--
-- Retorna as vitórias de um time.
--
getWinsByTeam :: Team -> [Match] -> Draws
getWinsByTeam _ [] = 0
getWinsByTeam team (match : matches) = do
    let ght = goalsHomeTeam match
    let gat = goalsAwayTeam match
    let wins | homeTeam match == team && ght > gat = updateWins $ getWinsByTeam team matches
             | awayTeam match == team && gat > ght = updateWins $ getWinsByTeam team matches
             | otherwise = getWinsByTeam team matches
    wins

--
-- Retorna os empates de um time.
--
getDrawsByTeam :: Team -> [Match] -> Draws
getDrawsByTeam _ [] = 0
getDrawsByTeam team (match : matches) = do
    let ght = goalsHomeTeam match
    let gat = goalsAwayTeam match
    let draws | homeTeam match == team && ght == gat = updateDraws $ getDrawsByTeam team matches
              | awayTeam match == team && gat == ght = updateDraws $ getDrawsByTeam team matches
              | otherwise = getDrawsByTeam team matches
    draws

--
-- Retorna as derrotas de um time.
--
getLossesByTeam :: Team -> [Match] -> Draws
getLossesByTeam _ [] = 0
getLossesByTeam team (match : matches) = do
    let ght = goalsHomeTeam match
    let gat = goalsAwayTeam match
    let losses | homeTeam match == team && ght < gat = updateLosses $ getLossesByTeam team matches
               | awayTeam match == team && gat < ght = updateLosses $ getLossesByTeam team matches
               | otherwise = getLossesByTeam team matches
    losses

--
-- Atualiza as vitórias de um time.
--
updateWins :: Wins -> Wins
updateWins wins = wins + 1

--
-- Atualiza as derrotas de um time.
--
updateLosses :: Losses -> Losses
updateLosses losses = losses + 1

--
-- Atualiza os empates de um time.
--
updateDraws :: Draws -> Draws
updateDraws draws = draws + 1

--
-- Calcula os pontos de um time específico.
--
getPointsByTeam :: Team -> [Match] -> Points
getPointsByTeam _ [] = 0
getPointsByTeam team matches = do
    let filtered = filterByTeam team matches
    let winnerPoints = getWinsByTeam team filtered * 3
    let drawsPoints = getDrawsByTeam team filtered
    winnerPoints + drawsPoints

--
-- Calcula o aproveitamento de um time específico.
--
getRecordsByTeam :: Team -> [Match] -> Record
getRecordsByTeam _ [] = 0
getRecordsByTeam team matches = do
    let filtered = filterByTeam team matches
    let record = fromIntegral (getPointsByTeam team filtered) * 100.0 / 54.0
    record

--
-- Retorna a quantidade de gols pró de um time específico.
--
getGoalsForByTeam :: Team -> [Match] -> Goals
getGoalsForByTeam _ [] = 0
getGoalsForByTeam team allMatches = do
    let (match : matches) = filterByTeam team allMatches
    let ht = homeTeam match
    let at = awayTeam match
    let ght = goalsHomeTeam match
    let gat = goalsAwayTeam match
    let goals | ht == team = updateGoals ght $ getGoalsForByTeam team matches
              | at == team = updateGoals gat $ getGoalsForByTeam team matches
              | otherwise = getGoalsForByTeam team matches
    goals

--
-- Retorna a quantidade de gols sofridos de um time específico.
--
getGoalsAgainstByTeam :: Team -> [Match] -> Goals
getGoalsAgainstByTeam _ [] = 0
getGoalsAgainstByTeam team allMatches = do
    let (match : matches) = filterByTeam team allMatches
    let ht = homeTeam match
    let at = awayTeam match
    let ght = goalsHomeTeam match
    let gat = goalsAwayTeam match
    let goals | ht == team = updateGoals gat $ getGoalsAgainstByTeam team matches
              | at == team = updateGoals ght $ getGoalsAgainstByTeam team matches
              | otherwise = getGoalsAgainstByTeam team matches
    goals

--
-- Retorna o saldo de gols de um time.
--
getGoalsDifferenceByTeam :: Team -> [Match] -> Goals
getGoalsDifferenceByTeam _ [] = 0
getGoalsDifferenceByTeam team matches = do
    getGoalsForByTeam team matches - getGoalsAgainstByTeam team matches

--
-- Atualiza os gols de um time.
--
updateGoals :: Goals -> Goals -> Goals
updateGoals previous goals = previous + goals
               
