---
--- Módulo responsável pelos utilitários que serão utilizados no projeto.
--- 
module App.Utils where

--- 
--- Limpa o console
---
clearScreen :: IO ()
clearScreen = putStr "\ESC[2J"

---
--- Cores do terminal
---
red :: String
green :: String
yellow :: String
blue :: String
purple :: String
reset :: String

---
--- Implementação das cores
---
red = "\27[1;31m"
green = "\27[1;32m"
yellow = "\27[1;33m"
blue = "\27[1;34m"
purple = "\27[1;35m"
reset = "\27[1;0m"
