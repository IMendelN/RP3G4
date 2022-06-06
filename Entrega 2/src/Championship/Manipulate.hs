module Championship.Manipulate where

import qualified Championship.ReadFile as File ( readDatabase, splitBy )
import Championship.Structures as Struct

--
-- Declaração de sinônimos para facilitar a leitura
-- de alguns métodos.
--
type Round = Integer
type Team = String
type Goals = Integer
type Winner = String

--
-- Transforma uma lista de String em uma "struct" de partida.
--
parseToMatch :: [String] -> [Match]
parseToMatch [] = []
parseToMatch fileLine = map (parseLine . File.splitBy ';') fileLine
    where
        parseLine line = Match (read (head line)) (line !! 1)
                               (read (line !! 2)) (read (line !! 3))
                               (line !! 4)

--
-- Retorna todas as partidas do arquivo de banco de dados
-- já no formato de "struct" de partida.
--
getAllMatches :: IO [Match]
getAllMatches = do
    fileContent <- File.readDatabase
    let matches = parseToMatch fileContent
    return matches

--
-- Filtra as partidas passando a rodada.
--
filterByRound :: Round -> [Match] -> [Match]
filterByRound round [] = []
filterByRound round matches = do
    filter (\match -> Struct.round match == round) matches

--
-- Mostra o time ganhador de uma rodada.
--
getWinnerByRound :: Round -> [Match] -> String
getWinnerByRound round [] = []
getWinnerByRound round (match : matches) = do
    let filtered = filterByRound round matches
    let hf = head filtered
    let action | goalsHomeTeam hf > goalsAwayTeam hf = homeTeam hf
               | otherwise = awayTeam hf
    action