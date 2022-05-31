---
--- Módulo responsável pela leitura e manipulação dos dados
--- contidos no campeonato.
---
module Championship.ReadFile where

import System.IO

---
--- TODO: Fazer uma função que retorne uma linha inteira.
---
readFile :: IO [String]
readFile = do 
    fileContent <- System.IO.readFile "..\\Database\\Campeonato.csv"
    let fileLine = lines fileContent
    return fileLine

