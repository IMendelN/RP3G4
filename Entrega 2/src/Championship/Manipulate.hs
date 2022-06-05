module Championship.Manipulate where

import qualified Championship.ReadFile as File ( readDatabase, splitBy )
import Championship.Structures (Match (Match))

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
