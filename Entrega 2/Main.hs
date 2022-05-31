module Main where

import System.IO

import Menu.Menu

main :: IO ()
main = do
    putStrLn readOnly


---
--- Versão que apenas imprime o conteúdo do arquivo.
--- OBS.: Apenas para testes. Quando estiver pronta,
--- esta será redirecionada a um módulo.
---
readOnly :: IO String
readOnly = do
    content <- openFile "/Championship/database/result.csv" ReadMode
    hGetContents content

