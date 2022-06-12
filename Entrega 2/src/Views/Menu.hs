module Views.Menu where

import System.IO ( hFlush, stdout )

import qualified Utils.Utils as U
import qualified Championship.Manipulate as M
import qualified Views.ShowResult as S
import Text.Read ( readMaybe )
import Championship.Manipulate (getResultByRoundAndTeam)
import Championship.Structures (Match(..))

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
            let checkNumber = read team
            let teamName = getTeamByIndex checkNumber
            let show = do 
                    U.cls
                    S.showTeamPerformance teamName $ M.getTeamPerformance teamName matches
            if checkNumber >= 1 && checkNumber <= 10 then
                show
            else do
                invalidOption
                menuOptions "1"
            returnToMenu
        "2" -> do
            listAllTeams
            putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
            hFlush stdout
            team <- getLine
            let checkNumber = read team
            let teamName = getTeamByIndex checkNumber
            let show = do 
                    U.cls
                    S.showRankTeam teamName
            if checkNumber >= 1 && checkNumber <= 10 then
                show
            else do
                invalidOption
                menuOptions "1"
            returnToMenu
        "3" -> do
            listAllTeams
            putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
            hFlush stdout
            team <- getLine
            let checkNumber = read team
            let teamName = getTeamByIndex checkNumber
            let show = do 
                    U.cls
                    S.showRecordsByTeam teamName matches
            if checkNumber >= 1 && checkNumber <= 10 then
                show
            else do
                invalidOption
                menuOptions "1"
            returnToMenu
        "4" -> do
            listAllTeams
            putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
            hFlush stdout
            team <- getLine
            let checkNumber = read team
            let teamName = getTeamByIndex checkNumber
            let show = do 
                    U.cls
                    S.showGoalsDifferenceByTeam teamName matches
            if checkNumber >= 1 && checkNumber <= 10 then
                show
            else do
                invalidOption
                menuOptions "1"
            returnToMenu
        "5" -> do
            putStr $ U.yellow ++ "Digite a rodada: " ++ U.reset -- Alterar para listagem de lista
            hFlush stdout
            round <- getLine
            let checkRound = read round
            if checkRound >= 1 && checkRound <= 18 then do
                U.cls
                menuOptionInsideFive checkRound matches
            else do
                invalidOption
                menuOptions "5"
            returnToMenu
        "6" -> do
            listAllTeams
            putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
            hFlush stdout
            team <- getLine
            let checkNumber = read team
            let teamName = getTeamByIndex checkNumber
            let show = do 
                    U.cls
                    S.showPointsByTeam teamName matches
            if checkNumber >= 1 && checkNumber <= 10 then
                show
            else do
                invalidOption
                menuOptions "1"
            returnToMenu
        "7" -> do
            M.storeTeamResult
            U.cls
            S.showPodium
            returnToMenu
        "8" -> do
            M.storeTeamResult
            U.cls
            S.showLastPlaces
            returnToMenu
        "9" -> do
            M.storeTeamResult
            U.cls
            S.showChampionshipResult
            returnToMenu
        "0" -> do
            exit
        _ -> do
            invalidOption
            menu

--
-- Retorna o nome do time através da escolha no menu de listagem.
--
getTeamByIndex :: Int -> String
getTeamByIndex team
    | team == 1 = "Botafogo"
    | team == 2 = "Figueirense"
    | team == 3 = "Guarani"
    | team == 4 = "Avai"
    | team == 5 = "Nautico"
    | team == 6 = "Cruzeiro"
    | team == 7 = "Confianca"
    | team == 8 = "Sampaio Correa"
    | team == 9 = "Oeste"
    | team == 10 = "CSA"
    | otherwise = "Sem time"

--
-- Imprime o número de rodadas.
--
listAllRounds :: IO ()
listAllRounds = do
    putStrLn $ U.purple ++ "[LISTA DE TIMES]\n" ++ U.reset
    putStrLn "+-------------------------------------------------------+"
    putStrLn "\t1 - Botafogo\t\t6  - Cruzeiro"
    putStrLn "\t2 - Figueirense\t\t7  - Confiança"
    putStrLn "\t3 - Guarani\t\t8  - Sampaio Correa"
    putStrLn "\t4 - Avai\t\t9  - Oeste"
    putStrLn "\t5 - Nautico\t\t10 - CSA"
    putStrLn "+-------------------------------------------------------+"

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
-- Mostra a segunda parte do quinto requisito de maneira separada
-- para tratamento de erro.
--
menuOptionInsideFive :: Integer -> [Match] -> IO ()
menuOptionInsideFive checkRound matches = do
    listAllTeams
    putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
    hFlush stdout
    team <- getLine
    let checkTeam = read team
    if checkTeam >= 1 && checkTeam <= 10 then do
        let teamName = getTeamByIndex checkTeam
        let result = getResultByRoundAndTeam checkRound teamName matches
        U.cls
        S.showResultByRoundAndTeam result
    else do
        U.cls
        invalidOption
        menuOptionInsideFive checkRound matches

--
-- Mostra uma mensagem de encerramento de 'programa'.
--
exit :: IO ()
exit = do
    U.cls
    putStrLn (U.blue ++ "\nPrograma encerrado." ++ U.reset)
