---
--- Módulo responsável pelos utilitários que serão utilizados no projeto.
--- 
module App.Utils where

import System.Console.ANSI

--- 
--- Limpa o console
---
clearScreen :: IO ()
clearScreen = putStr "\ESC[2J"

---
--- Cores do terminal.
---
red :: IO ()
red = putStr "\27[1;31m"
green :: IO ()
green = putStr "\27[1;32m"
yellow :: IO ()
yellow = putStr "\27[1;33m"
blue :: IO ()
blue = putStr "\27[1;34m"
purple :: IO ()
purple = putStr "\27[1;35m"
reset :: IO ()
reset = putStr "\27[1;0m"
