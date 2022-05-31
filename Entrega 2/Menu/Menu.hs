---
--- Módulo responsável em apresentar o menu de opções para o usuário.
--- 
module Menu.Menu where

import System.Console.ANSI
import System.IO ()

import App.Utils

test :: String
test = "ola mundo"

menu :: IO ()
menu = do
    App.Utils.clearScreen
    purple 
    putStrLn "[Bem-vindo ao Campeonato UNIPAMPA]\n" 
    reset
    putStrLn "Menu de opções:\n"
    putStrLn "\t1 - Visualizar todos os resultados"
    putStrLn "\t2 - Visualizar os 3 primeiros colocados"
    putStrLn "\t3 - Visualizar os 3 últimos colocados"
    putStrLn "\t4 - Visualizar a classificação geral"
    putStrLn "\t5 - Visualizar opções por time"
    putStrLn "\t0 - Sair"
    yellow
    putStr "\nDigite uma das opções acima: "
    reset
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
            putStrLn "\nPrograma encerrado."
        _ -> do
            App.Utils.clearScreen
            menu

