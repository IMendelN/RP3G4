module Views.Menu where

import System.IO ( hFlush, stdout )

import qualified Utils.Utils as U
import qualified Championship.Manipulate as M
import qualified Views.ShowResult as S

--
-- Menu principal.
--
menu :: IO ()
menu = do
    putStrLn $ U.purple ++ "[BEM-VINDO AO CAMPEONATO UNIPAMPA]\n" ++ U.reset
    putStrLn "Menu de opções:\n"
    putStrLn "\t 1 - Número de vitórias, empates e derrotas do time X no campeonato?"
    putStrLn "\t 2 - Classificação do time X no campeonato?"
    putStrLn "\t 3 - Aproveitamento do time X no campeonato?"
    putStrLn "\t 4 - Saldo de gols do time X no campeonato?"
    putStrLn "\t 5 - Resultado da partida da rodada N do time X no campeonato?"
    putStrLn "\t 6 - Número de pontos do time X no campeonato?"
    putStrLn "\t 7 - Times que estão nas primeiras 3 colocações?"
    putStrLn "\t 8 - Times rebaixados?"
    putStrLn "\t 9 - Classificação geral do campeonato?"
    putStrLn "\t10 - Consultar regulamento sobre o campeonato?"
    putStrLn "\t 0 - Sair"
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
                 S.showTeamPerformance teamName $ M.getTeamPerformance teamName matches
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
                _ -> do
                    invalidOption
                    menuOptions "1"
            returnToMenu
        "2" -> do
            U.cls
            menu
        "3" -> do
            listAllTeams
            putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
            hFlush stdout
            team <- getLine
            let teamName = getTeamByIndex team
            let show = do
                    U.cls
                    S.showRecordsByTeam teamName matches
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
                _ -> do
                    invalidOption
                    menuOptions "1"
            returnToMenu
        "4" -> do
            listAllTeams
            putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
            hFlush stdout
            team <- getLine
            let teamName = getTeamByIndex team
            let show = do
                    U.cls
                    S.showGoalsDifferenceByTeam teamName matches
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
                _ -> do
                    invalidOption
                    menuOptions "1"
            returnToMenu
        "5" -> do
            listAllRound
            putStr $ U.yellow ++ "Digite uma das opções acima para rodada: " ++ U.reset
            hFlush stdout
            round <- getLine
            listAllTeams
            putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
            hFlush stdout
            team <- getLine
            let teamName = getTeamByIndex team
            let result = M.getResultByRoundAndTeam (read round) teamName matches
            let show = do
                    U.cls
                    S.showResultByRoundAndTeam result
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
                _ -> do
                    invalidOption
                    menuOptions "1"
            returnToMenu
        "6" -> do
            listAllTeams
            putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
            hFlush stdout
            team <- getLine
            let teamName = getTeamByIndex team
            let show = do
                     U.cls
                     S.showPointsByTeam teamName matches
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
                _ -> do
                    invalidOption
                    menuOptions "1"
            returnToMenu
        "7" -> do
            U.cls
            menu
        "8" -> do
            U.cls
            menu
        "9" -> do
            U.cls
            menu
        "10" -> do
            informationRegulation
            returnToMenu
        "0" -> do
            exit
        _ -> do
            invalidOption
            menu

--
--Listar Rodadas
--
listAllRound :: IO()
listAllRound = do
    putStrLn $ U.purple ++ "[LISTA DE RODADAS]\n" ++ U.reset
    putStrLn "+-------------------------------------------------------+"
    putStrLn "\t1 - Rodada 1\t\t7  - Rodada 7\t\t13  - Rodada 13"
    putStrLn "\t2 - Rodada 2\t\t8  - Rodada 8\t\t14  - Rodada 14"
    putStrLn "\t3 - Rodada 3\t\t9  - Rodada 9\t\t15  - Rodada 15"
    putStrLn "\t4 - Rodada 4\t\t10 - Rodada 10\t\t16  - Rodada 16"
    putStrLn "\t5 - Rodada 5\t\t11 - Rodada 11\t\t17  - Rodada 17"
    putStrLn "\t6 - Rodada 6\t\t12 - Rodada 12\t\t18  - Rodada 18"
    putStrLn "+-------------------------------------------------------+"

--
-- Retorna o nome do time através da escolha no menu de listagem.
--
getTeamByIndex :: String -> String
getTeamByIndex team
    | team == "1" = "Botafogo"
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
    putStrLn "\t1 - Botafogo\t\t6  - Cruzeiro"
    putStrLn "\t2 - Figueirense\t\t7  - Confiança"
    putStrLn "\t3 - Guarani\t\t8  - Sampaio Correa"
    putStrLn "\t4 - Avai\t\t9  - Oeste"
    putStrLn "\t5 - Nautico\t\t10 - CSA"
    putStrLn "+-------------------------------------------------------+"

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

--
-- Mostra uma mensagem de encerramento de 'programa'.
--
exit :: IO ()
exit = do
    U.cls
    putStrLn (U.blue ++ "\nPrograma encerrado." ++ U.reset)

--
-- Informações gerais sobre o campeonato
--
informationRegulation :: IO ()
informationRegulation = do
    putStrLn $ U.purple ++ "[INFORMAÇÕES GERAIS]\n" ++ U.reset
    putStrLn "+------------------------------------------------------------------------+"
    putStrLn "\tO campeonato é composto por 10 times."
    putStrLn "\tOs times se enfrentam em turno e returno."
    putStrLn "\tO vencedor de cada partida ganha 3 pontos."
    putStrLn "\tEm caso de empate cada time ganha 1 ponto."
    putStrLn "\tOs times são ranqueados por pontuação."
    putStrLn $ U.green ++ "\tO time com maior pontuação é o campeão." ++ U.reset
    putStrLn $ U.red ++ "\tOs 3 times com menor pontuação são rebaixados." ++ U.reset
    putStrLn "\tCaso haja empate na pontuação, aplicam-se os seguintes critérios: "
    putStrLn "\t - Número de vitórias"
    putStrLn "\t - Saldo de gols;"
    putStrLn "\t - Gols pró;"
    putStrLn "+------------------------------------------------------------------------+"