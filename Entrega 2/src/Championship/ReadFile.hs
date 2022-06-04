module Championship.ReadFile where

import qualified System.IO as IO

import Championship.Structures
import Championship.Types

readDatabase :: IO [String]
readDatabase = do
    fileContent <- IO.readFile "app/Championship/database/result.csv"
    let content = lines fileContent
    return content

getMatches :: IO [Match]
getMatches = do
    fileLine <- readDatabase
    let matches = parseToMatch fileLine
    return matches

split :: Delimiter -> String -> [String]
split _ "" = []
split delimiter string =
    let 
        (start, rest) = break (== delimiter) string
        (_, remain) = span (== delimiter) rest
    in 
        start : split delimiter remain

parseToMatch :: [String] -> [Match]
parseToMatch [] = []
parseToMatch fileLine = map (parseLine . split ';') fileLine

parseLine :: [String] -> Match
parseLine line = Match (read (head line)) (line !! 1) (read (line !! 2)) (read (line !! 3)) (line !! 4)

