---
--- Módulo responsável em apresentar o menu de opções para o usuário.
--- 
module Menu.Menu where

import System.IO ()

import App.Utils

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
            putStrLn (blue ++ "\nPrograma encerrado." ++ reset)
        _ -> do
            optionInvalid
            menu

optionInvalid :: IO ()
optionInvalid = do
    App.Utils.clearScreen
    putStrLn (red ++ "* Por favor, digite uma opção válida.\n" ++ reset)