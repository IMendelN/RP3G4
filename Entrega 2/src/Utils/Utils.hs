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
brown :: String
gray :: String
reset :: String

---
--- Implementação das cores
---
gray = "\27[0;30m"
red = "\27[0;31m"
green = "\27[0;32m"
yellow = "\27[0;33m"
blue = "\27[0;34m"
purple = "\27[1;35m"
cyan = "\27[0;36m"
white = "\27[4;37m"
brown = "\27[22;3m"
reset = "\27[0;0m"