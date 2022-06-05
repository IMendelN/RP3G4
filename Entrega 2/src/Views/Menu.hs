module Views.Menu where

import System.IO ( hFlush, stdout )

import Utils.Utils as Utils
import Championship.ReadFile as File

--
-- Menu principal.
--
menu :: IO ()
menu = do
    putStrLn (purple ++ "[BEM-VINDO AO CAMPEONATO UNIPAMPA]\n" ++ reset) 
    putStrLn "Menu de opções:\n"
    putStrLn "\t1 - Visualizar o pódio"
    putStrLn "\t2 - Visualizar times rebaixados"
    putStrLn "\t3 - Visualizar a classificação geral"
    putStrLn "\t4 - Visualizar opções por time"
    putStrLn "\t0 - Sair"
    putStr (yellow ++ "\nDigite uma das opções acima: " ++ reset)
    hFlush stdout
    option <- getLine
    menuOptions option

--
-- Menu de escolha para o menu principal.
--
menuOptions :: String -> IO ()
menuOptions option = do
    case option of
        "1" -> do
            Utils.clearScreen
            putStrLn "I'm not doing anything... yet."
        "2" -> do
            Utils.clearScreen
            menu
        "3" -> do
            Utils.clearScreen
            menu
        "4" -> do
            Utils.clearScreen
            menu
        "0" -> do
            putStrLn (blue ++ "\nPrograma encerrado." ++ reset)
        _ -> do
            optionInvalid
            menu

--
-- Menu para visualizar opções de times individualmente.
--
allTeams :: IO ()
allTeams = do
    Utils.clearScreen
    putStrLn (purple ++ "[OPÇÕES POR TIME]\n" ++ reset) 
    putStrLn "Menu de opções:\n"
    putStrLn "\t1 - Visualizar vitórias, derrotas e empates"
    putStrLn "\t2 - Classificação do time"
    putStrLn "\t3 - Aproveitamento do time"
    putStrLn "\t4 - Visualizar opções por time"
    putStrLn "\t0 - Sair"
    putStr (yellow ++ "\nDigite uma das opções acima: " ++ reset)
    hFlush stdout
    option <- getLine
    menuOptions option

--
-- Mensagem de erro para opções inválidas de menu.
--
optionInvalid :: IO ()
optionInvalid = do
    Utils.clearScreen
    putStrLn (red ++ "* Por favor, digite uma opção válida.\n" ++ reset)