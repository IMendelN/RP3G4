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
    U.putStrLnColor "purple" "[BEM-VINDO AO CAMPEONATO UNIPAMPA]\n"
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
    U.putStrColor "yellow" "\nDigite uma das opções acima: "
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
            U.putStrColor "yellow" "\nDigite uma das opções acima: "
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
            U.putStrColor "yellow" "\nDigite uma das opções acima: "
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
            U.putStrColor "yellow" "\nDigite uma das opções acima: "
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
            U.putStrColor "yellow" "\nDigite uma das opções acima: "
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
            U.putStrColor "yellow" "Digite a rodada: " -- Alterar para listagem de lista
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
            U.putStrColor "yellow" "\nDigite uma das opções acima: "
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
    U.putStrLnColor "purple" "[LISTA DE TIMES]\n"
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
    U.putStrLnColor "purple" "[LISTA DE TIMES]\n"
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
    U.putStrLnColor "red" "* Por favor, digite uma opção válida.\n"

--
-- Opção para retornar ao menu principal
--
returnToMenu :: IO ()
returnToMenu = do
    U.putStrColor "white" "\nDeseja retornar ao menu principal? (S/N):"
    putStr " "
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
    U.putStrColor "yellow" "\nDigite uma das opções acima: "
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