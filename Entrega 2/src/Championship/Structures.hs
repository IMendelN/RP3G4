module Championship.Structures where

---
--- Criação de uma "struct" responsável em armazenar informações
--- sobre uma determinada partida.
---
data Match = Match {
    _round :: Integer,
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
    wins :: Float,
    draws :: Float,
    losses :: Float,
    points :: Float,
    record :: Float
} deriving (Show)
