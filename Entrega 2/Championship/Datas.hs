module Championship.Datas where

---
--- Criação de um "dado" responsável em armazenar informações
--- sobre uma determinada partida.
--- 
--- Nele, possui a mesma quantidade de dados do arquivo CSV e
--- em sua devida ordem.
---
data Match = Match {
    round :: Integer,
    homeTeam :: String,
    goalsHomeTeam :: Integer,
    goalsAwayTeam :: Integer,
    awayTeam :: String
} deriving (Show)

---
--- Criação de um "dado" responsável em armazenar informações
--- sobre o campeonato em geral.
---
data Result = Result {
    rank :: Integer,
    goals :: Integer
} deriving (Show)