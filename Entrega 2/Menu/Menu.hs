module Menu.Menu where

import System.IO ()

import qualified App.Utils

menu :: IO ()
menu = do
    putStrLn "------------------------- Bem vindo ao Campeonato UNIPAMPA ---------------------------\n"
    putStrLn "\nDigite 1 visualizar todos os resultados"
    putStrLn "\t1 - Visualizar os 3 primeiro colocados"
    putStrLn "\t2 - Visualizar os 3 ultimos colocados"
    putStrLn "\t3 - Visualizar a classificacao geral"
    putStrLn "\t4 - Visualizar opções por time"
    putStrLn "\t0 - Sair"
    putStr "Opção: "
    option <- getLine
    menuOptions option

menuOptions :: String -> IO ()
menuOptions option = do
    case option of
        "1" -> do
            -- TODO
            menu
        "2" -> do
            -- TODO
            menu
        "3" -> do
            -- TODO
            menu
        "4" -> do
            -- TODO
            menu
        "0" -> do
            App.Utils.clear
            putStrLn "\nPrograma encerrado."
        _ -> do
            menu

