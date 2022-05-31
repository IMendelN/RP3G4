module Main where

import System.IO

import Menu.Menu
import Championship.ReadFile

main :: IO ()
main = do 
    readOnly

---
--- Versão que apenas imprime o conteúdo do arquivo.
--- OBS.: Apenas para testes. Quando estiver pronta,
--- esta será redirecionada a um módulo.
---
readOnly :: IO ()
readOnly = do
    content <- System.IO.readFile "Championship\\database\\result.csv"
    putStrLn content
