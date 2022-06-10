module Views.ShowResult where

import Championship.Manipulate as M
import qualified Utils.Utils as U
import Championship.Structures

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
