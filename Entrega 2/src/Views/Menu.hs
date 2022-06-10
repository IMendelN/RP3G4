module Views.Menu where

import System.IO ( hFlush, stdout )

import qualified Utils.Utils as U
import qualified Championship.Manipulate as M
import qualified GHC.IO.Exception as System

--
-- Menu principal.
--
menu :: IO ()
menu = do
    putStrLn $ U.purple ++ "[BEM-VINDO AO CAMPEONATO UNIPAMPA]\n" ++ U.reset
    putStrLn "Menu de opções:\n"
    putStrLn "\t1 - Número de vitórias, empates e derrotas do time X no campeonato?"
    putStrLn "\t2 - Classificação do time X no campeonato?"
    putStrLn "\t3 - Aproveitamento do time X no campeonato?"
    putStrLn "\t4 - Saldo de gols do time X no campeonato?"
    putStrLn "\t5 - Resultado da partida da rodada N do time X no campeonato?"
    putStrLn "\t6 - Número de pontos do time X no campeonato?"
    putStrLn "\t7 - Times que estão nas primeiras 3 colocações?"
    putStrLn "\t8 - Times rebaixados?"
    putStrLn "\t9 - Classificação geral do campeonato?"
    putStrLn "\t0 - Sair"
    putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
    hFlush stdout
    option <- getLine
    U.cls
    menuOptions option

--
-- Menu de escolha para o menu principal.
--
menuOptions :: String -> IO ()
menuOptions option = do
    matches <- M.getMatches
    case option of
        "1" -> do
            listAllTeams
            putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
            hFlush stdout
            team <- getLine
            let teamName = getTeamByIndex team
            let show = do
                U.cls
                M.showTeamPerformance teamName $ M.getTeamPerformance teamName matches
            case team of
                "1" -> show
                "2" -> show
                "3" -> show
                "4" -> show
                "5" -> show
                "6" -> show
                "7" -> show
                "8" -> show
                "9" -> show
                "10" -> show
                "0" -> putStr ""
                _ -> do
                    invalidOption
                    menuOptions "1"
            returnToMenu
        "2" -> do
            U.cls
            menu
        "3" -> do
            U.cls
            menu
        "4" -> do
            U.cls
            menu
        "5" -> do
            putStr $ U.yellow ++ "Digite a rodada: " ++ U.reset
            hFlush stdout
            round <- getLine
            putStr $ U.yellow ++ "Digite o nome do time: " ++ U.reset
            hFlush stdout
            team <- getLine
            let result = M.getResultByRoundAndTeam (read round) team matches
            M.showResultByRoundAndTeam result
            returnToMenu
        "0" -> do
            exit
        _ -> do
            invalidOption
            menu

--
-- Retorna o nome time através do menu de listagem.
--
getTeamByIndex :: String -> String
getTeamByIndex team
    | team == "1" = "Botafogo - SP"
    | team == "2" = "Figueirense"
    | team == "3" = "Guarani"
    | team == "4" = "Avai"
    | team == "5" = "Nautico"
    | team == "6" = "Cruzeiro"
    | team == "7" = "Confianca"
    | team == "8" = "Sampaio Correa"
    | team == "9" = "Oeste"
    | team == "10" = "CSA"
    | otherwise = "Sem time"

--
-- Lista todos os times do campeonato.
--
listAllTeams :: IO ()
listAllTeams = do
    putStrLn $ U.purple ++ "[LISTA DE TIMES]\n" ++ U.reset
    putStrLn "+-------------------------------------------------------+"
    putStrLn "\t1 - Botafogo - SP\t6  - Cruzeiro"
    putStrLn "\t2 - Figueirense\t\t7  - Confiança"
    putStrLn "\t3 - Guarani\t\t8  - Sampaio Correa"
    putStrLn "\t4 - Avai\t\t9  - Oeste"
    putStrLn "\t5 - Nautico\t\t10 - CSA"
    putStrLn "+-------------------------------------------------------+"
    putStrLn "\t0 - Sair"

--
-- Mensagem de erro para opções inválidas de menu.
--
invalidOption :: IO ()
invalidOption = do
    U.cls
    putStrLn $ U.red ++ "* Por favor, digite uma opção válida.\n" ++ U.reset

--
-- Opção para retornar ao menu principal
--
returnToMenu :: IO ()
returnToMenu = do
    putStr $ U.white ++ "\nDeseja retornar ao menu principal? (S/N):" ++ U.reset ++ " "
    hFlush stdout
    option <- getLine
    U.cls
    let confirm | option == "S" || option == "s" = menu
                | otherwise = putStrLn $ U.blue ++ "Programa encerrado." ++ U.reset
    confirm

exit :: IO ()
exit = do
    U.cls
    putStrLn (U.blue ++ "\nPrograma encerrado." ++ U.reset)
