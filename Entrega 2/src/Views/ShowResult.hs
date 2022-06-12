module Views.ShowResult where

import Text.Printf

import Championship.Manipulate as M
import qualified Utils.Utils as U
import Championship.Structures ( Match(..), TeamResult (..) )

--
-- Imprime o resultado do desempenho de um time específico (RF1).
--
showTeamPerformance :: Team -> (Wins, Draws, Losses) -> IO ()
showTeamPerformance team (wins, draws, losses) = do
    putStrLn $ U.purple ++ "\n[DESEMPENHO DO TIME]\n" ++ U.reset
    putStrLn $ U.blue ++ "> Time: " ++ team ++ U.reset
    putStrLn "+----------------------------------------+"
    putStrLn $ U.green ++ "  Vitórias: " ++ show wins ++ U.reset
    putStrLn $ "  Empates: " ++ show draws
    putStrLn $ U.red ++ "  Derrotas: " ++ show losses ++ U.reset
    putStrLn "+----------------------------------------+"

--
-- Imprime o aproveitamento de um time específicado (RF3).
--
showRecordsByTeam :: Team -> [Match] -> IO ()
showRecordsByTeam _ [] = putStrLn $ U.red ++ "Não há pontuação." ++ U.reset
showRecordsByTeam team matches = do
    let filtered = M.filterByTeam team matches
    let records = M.getRecordsByTeam team filtered
    putStrLn $ U.purple ++ "\n[APROVEITAMENTO DO TIME]\n" ++ U.reset
    putStrLn "+----------------------------------------------+"
    putStr $ "  " ++ U.blue ++ team ++ " teve "
    printf "%.2g" records
    putStrLn $ " % de aproveitamento." ++ U.reset
    putStrLn "+----------------------------------------------+"

--
-- Imprime o saldo de gols de um time específicado (RF4).
--
showGoalsDifferenceByTeam :: Team -> [Match] -> IO ()
showGoalsDifferenceByTeam _ [] = putStrLn $ U.red ++ "Não há pontuação." ++ U.reset
showGoalsDifferenceByTeam team matches = do
    putStrLn $ U.purple ++ "\n[SALDO DE GOLS DO TIME]\n" ++ U.reset
    putStrLn $ U.blue ++ "> Time: " ++ team ++ U.reset
    putStrLn "+----------------------------------------+"
    putStr U.cyan
    putStrLn $ "  O saldo é de " ++ show (getGoalsDifferenceByTeam team matches) ++ " gols."
    putStr U.reset
    putStrLn "+----------------------------------------+"

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
    putStrLn $ M.getWinnerByRoundAndTeam match ++ U.reset

--
-- Imprime a pontuação de um time específicado (RF6).
--
showPointsByTeam :: Team -> [Match] -> IO ()
showPointsByTeam _ [] = putStrLn $ U.red ++ "Não há pontuação." ++ U.reset
showPointsByTeam team matches = do
    let filtered = M.filterByTeam team matches
    let points = M.getPointsByTeam team filtered
    putStrLn $ U.purple ++ "\n[PONTUAÇÃO DO TIME]\n" ++ U.reset
    putStrLn "+----------------------------------------+"
    putStrLn $ "  " ++ U.blue ++ team ++ " possui " ++ show points ++ " pontos." ++ U.reset
    putStrLn "+----------------------------------------+"

--
-- Imprime os três primeiros colocados (RF7).
--
showPodium :: IO ()
showPodium = do
    teams <- getTeamResult
    let result = sortTeamResult teams
    putStrLn $ U.purple ++ "\n[PÓDIO DO CAMPEONATO]\n" ++ U.reset
    putStrLn "+----------------------------------------+"
    putStrLn $ U.green ++ "  1º colocado: " ++ team (head result) ++ U.reset
    putStrLn $ "  2º colocado: " ++ team (result !! 1)
    putStrLn $ "  3º colocado: " ++ team (result !! 2) ++ U.reset
    putStrLn "+----------------------------------------+"

--
-- Imprime os três últimos colocados (RF8)
--
showLastPlaces :: IO ()
showLastPlaces = do
    teams <- getTeamResult
    let result = sortTeamResult teams
    putStrLn $ U.purple ++ "\n[ÚLTIMOS COLOCADOS DO CAMPEONATO]\n" ++ U.reset
    putStrLn "+----------------------------------------+"
    putStrLn $ U.red ++ "   8º colocado: " ++ team (result !! 7)
    putStrLn $ "   9º colocado: " ++ team (result !! 8)
    putStrLn $ "  10º colocado: " ++ team (result !! 9) ++ U.reset
    putStrLn "+----------------------------------------+"

--
-- Imprime o resultado geral do campeonato (RF9).
--
showChampionshipResult :: IO ()
showChampionshipResult = do
    teams <- getTeamResult
    let rank = sortTeamResult teams
    putStrLn $ U.purple ++ "\n[RESULTADO DO CAMPEONATO]\n" ++ U.reset
    putStr U.blue
    printf "   \t\t\t   |  %2s  |  %2s  |  %2s  |  %2s  |  %3s  |  %2s  | %3s |\n" 
        "VI" "EM" "DE" "GP" "SG" "PT" "APR (%)"
    putStr U.reset
    formatResult 1 rank

--
-- Formata e organiza o resultado geral do campeonato (RF9).
--
formatResult :: Int -> [TeamResult] -> IO ()
formatResult _ [] = return ()
formatResult rank (team : teams) = do
    let color | rank >= 1 && rank <= 3 = putStr U.green 
              | rank >= 8 && rank <= 10 = putStr U.red
              | otherwise = putStr U.reset
    color
    formatTeam rank team
    putStr U.reset
    formatResult (rank + 1) teams 

--
-- Formata as informações de um determinado time (RF9).
--
formatTeam :: Int -> TeamResult -> IO ()
formatTeam rank t = do
    let tab | rank >= 1 && rank <= 5 || rank == 6 || rank == 8 = printf "   %2dº - %s\t" 
            | otherwise = printf "   %2dº - %s\t\t"
    tab rank (team t)
    printf "   |  %2d  |  %2d  |  %2d  |  %2d  |  %3d  |  %2d  |  %.2f  |\n" 
        (wins t) (draws t) (losses t) (goals t) (goalDiff t) (points t) (record t)
