module Views.Menu where

import System.IO ( hFlush, stdout )

import Utils.Utils as U
import Championship.ReadFile as F
import Championship.Structures as S
import Championship.Manipulate as M

--
-- Menu principal.
--
menu :: IO ()
menu = do
    putStrLn $ U.purple ++ "[BEM-VINDO AO CAMPEONATO UNIPAMPA]\n" ++ U.reset
    putStrLn "Menu de opções:\n"
    putStrLn "\t1 - Visualizar o pódio"
    putStrLn "\t2 - Visualizar times rebaixados"
    putStrLn "\t3 - Visualizar a classificação geral"
    putStrLn "\t4 - Visualizar opções por time"
    putStrLn "\t5 - Resultado de uma partida X"
    putStrLn "\t0 - Sair"
    putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
    hFlush stdout
    option <- getLine
    menuOptions option

--
-- Menu de escolha para o menu principal.
--
menuOptions :: String -> IO ()
menuOptions option = do
    matches <- getMatches
    case option of
        "1" -> do
            U.cls
            menu
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
            let result = getResultByRoundAndTeam (read round) team matches
            showResultByRoundAndTeam result
        "0" -> do
            putStrLn (U.blue ++ "\nPrograma encerrado." ++ U.reset)
        _ -> do
            optionInvalid
            menu

--
-- Menu para visualizar opções de times individualmente.
--
allTeams :: IO ()
allTeams = do
    U.cls
    putStrLn $ U.purple ++ "[OPÇÕES POR TIME]\n" ++ U.reset
    putStrLn "Menu de opções:\n"
    putStrLn "\t1 - Visualizar vitórias, derrotas e empates"
    putStrLn "\t2 - Classificação do time"
    putStrLn "\t3 - Aproveitamento do time"
    putStrLn "\t4 - Visualizar opções por time"
    putStrLn "\t0 - Sair"
    putStr $ U.yellow ++ "\nDigite uma das opções acima: " ++ U.reset
    hFlush stdout
    option <- getLine
    menuOptions option

--
-- Mensagem de erro para opções inválidas de menu.
--
optionInvalid :: IO ()
optionInvalid = do
    U.cls
    putStrLn $ U.red ++ "* Por favor, digite uma opção válida.\n" ++ U.reset
