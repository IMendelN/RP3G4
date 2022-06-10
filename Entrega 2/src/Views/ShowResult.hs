
module Views.ShowResult where

import Championship.Manipulate as M
import  Utils.Utils as U
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


--
--Imprime o aproveitamento de um time específicado (RF3).
--
showAproveitamentoByTeam :: Team -> [Match] -> IO ()
showAproveitamentoByTeam _ [] = putStrLn $ U.red ++ "Não há pontuação." ++ U.reset
showAproveitamentoByTeam team matches = do
    let filtered = M.filterByTeam team matches
    let aprov = M.getRecordByTeam team filtered
    putStrLn $ U.purple ++ "\n[APROVEITAMENTO DO TIME]\n" ++ U.reset
    putStrLn "+----------------------------------------+"
    putStrLn $ "  " ++ U.blue ++ team ++ " possui " ++ show aprov++ " %. de aproveitamento" ++ U.reset
    putStrLn "+----------------------------------------+"


--
-- Imprimi Saldo de goals mas esta pegando só da rodada 1 por enquanto. ( isso nao é um RF de mostrar na tela )
{--
showBalanceByTeam ::Team -> [Match] -> IO ()
showBalanceByTeam _ [] = putStrLn $ U.red ++ "Não há resultados." ++ U.reset
showBalanceByTeam team matches = do
    let filtered = M.filterByTeam team matches
    let saldo = M.getBalenceGoalsByTeam team filtered
    putStrLn $ U.purple ++ "\n[SALDO DE GOLS DO TIME]\n" ++ U.reset
    putStrLn "+----------------------------------------+"
    putStrLn $ "  " ++ U.blue ++ team ++ " possui " ++ show saldo++ " de saldo de golas" ++ U.reset
    putStrLn "+----------------------------------------+" -}