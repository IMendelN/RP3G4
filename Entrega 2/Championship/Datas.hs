module Championship.Datas where

---
--- Criação de um "dado" responsável em armazenar informações
--- sobre uma determinada partida.
---
data Match = Match {
    round :: Integer
    -- TODO
} deriving (Show)

---
--- Criação de um "dado" responsável em armazenar informações
--- sobre o campeonato em geral.
---
data Result = Result {
    -- TODO
} deriving (Show)