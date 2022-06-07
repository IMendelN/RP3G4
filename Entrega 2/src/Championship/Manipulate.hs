module Championship.Manipulate where

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
-- Imprime o nome do time vencedor de uma partida específica.
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
    putStrLn $ getWinnerByRoundAndTeam match

--
-- Imprime os 3 primeiros colocados
--
timeClassification :: MatchResult -> IO ()
timeClassification matchresult = do
    putStrLn $ U.green ++ "\n[TIMES NO PÓDIO]\n" ++ U.reset
    putStrLn "+----------------------------------------+"
    putStr $ " " ++ U.blue ++ take[3..] > points ++ " | " ++ show (rank points) ++ "  |  " ++ show (wins)
    putStrLn "+----------------------------------------+"
    
--
-- Imprime os 3 ultimos colocados
--
timeDesclasifield :: MatchResult -> IO ()
timeDesclasifield matchresultU = do
    putStrLn $ U.yellow ++ "\n[TIMES NO PÓDIO DE BAIXO]\n" ++ U.reset
    putStrLn "+----------------------------------------+"
    putStr $ "  " ++ U.red ++ take [3..] < points ++ " |  " ++ show (team points) ++ "  |  " ++ show (rank)
    putStrLn "+----------------------------------------+"

--
-- Imprime a posicao do time
--
timePosicaoTabela :: MatchResult -> IO ()
timePosicaoTabela matchresultPT = do
    putStrLn $ U.blue ++ "\n[A POSICAO DO TIME E]\n" ++ U.reset
    putStrLn "+----------------------------------------+"
    putStr $ "  " ++ U.green ++ take [1] == rank ++ " | " ++ show (team points) ++ " | " ++ show (rank)
    putStrLn "+----------------------------------------+"

