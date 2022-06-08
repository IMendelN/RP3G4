module Utils.Utils where

--- 
--- Limpa o console
---
cls :: IO ()
cls = putStr "\ESC[2J"

---
--- Cores do terminal
---
red :: String
green :: String
yellow :: String
blue :: String
purple :: String
cyan :: String
white :: String
reset :: String

---
--- Implementação das cores
---
red = "\27[1;31m"
green = "\27[1;32m"
yellow = "\27[1;33m"
blue = "\27[1;34m"
purple = "\27[1;35m"
cyan = "\27[1;36m"
white = "\27[1;37m"
reset = "\27[1;0m"