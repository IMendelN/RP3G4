module Views.Menu where

import System.IO ( hFlush, stdout )

import Utils.Utils as Utils
import Championship.ReadFile as File

menu :: IO ()
menu = do
    putStrLn (purple ++ "[Bem-vindo ao Campeonato UNIPAMPA]\n" ++ reset) 
    putStrLn "Menu de opções:\n"
    putStrLn "\t1 - Visualizar os 3 primeiros colocados"
    putStrLn "\t2 - Visualizar os 3 últimos colocados"
    putStrLn "\t3 - Visualizar a classificação geral"
    putStrLn "\t4 - Visualizar opções por time"
    putStrLn "\t0 - Sair"
    putStr (yellow ++ "\nDigite uma das opções acima: " ++ reset)
    hFlush stdout
    option <- getLine
    menuOptions option

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

optionInvalid :: IO ()
optionInvalid = do
    Utils.clearScreen
    putStrLn (red ++ "* Por favor, digite uma opção válida.\n" ++ reset)