module Championship.ReadFile where

import qualified System.IO as IO

import Championship.Structures

type Delimiter = Char

--
-- Realiza a leitura do arquivo de banco de dados do projeto.
--
readDatabase :: IO [String]
readDatabase = do
    fileContent <- IO.readFile "src/Championship/database/result.csv"
    let content = lines fileContent
    return content

--
-- Transforma um texto que contém um delimitador em uma lista 
-- de String com os respectivos elementos separados.
--
splitBy :: Delimiter -> String -> [String]
splitBy delimiter [] = [""]
splitBy delimiter (x : xs)
        | x == delimiter = "" : rest
        | otherwise = (x : head rest) : tail rest
    where
        rest = splitBy delimiter xs

--
-- Transforma uma lista de String em uma "struct" de partida.
--
parseToMatch :: [String] -> [Match]
parseToMatch [] = []
parseToMatch fileLine = map (parseLine . splitBy ';') fileLine
    where
        parseLine line = Match (read (head line)) (line !! 1) 
                               (read (line !! 2)) (read (line !! 3)) 
                               (line !! 4)
