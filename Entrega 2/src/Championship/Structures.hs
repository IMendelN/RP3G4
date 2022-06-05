module Championship.Structures where

---
--- Criação de uma "struct" responsável em armazenar informações
--- sobre uma determinada partida.
---
data Match = Match {
    round :: Integer,
    homeTeam :: String,
    goalsHomeTeam :: Integer,
    goalsAwayTeam :: Integer,
    awayTeam :: String
} deriving (Show)

---
--- Criação de uma "struct" responsável em armazenar informações
--- sobre o campeonato em geral.
---
data MatchResult = MatchResult {
    rank :: Integer,
    goals :: Integer,
    wins :: Integer,
    draws :: Integer,
    losses :: Integer,
    points :: Integer
} deriving (Show)
