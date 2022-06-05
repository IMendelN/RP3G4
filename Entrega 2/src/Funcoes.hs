module Funcoes where
import Control.Monad
import Control.Monad.Trans

--Qual o número de vitórias, empates e derrotas do time X no campeonato?
dadosTime :: int -> int -> int 
dadosTime x y = x+y


--Pontuação da Partida
resultado :: Int -> Int -> Int
resultado golP golC             | golP > golC = 3
                                | golP == golC = 1
                                | otherwise = 0

--Qual a classificação do time X no campeonato?
classificacaoTime :: Int -> Int -> Int
classificacaoTime x y = x + y


--Qual o aproveitamento do time X no campeonato?
aproveitamentoTime :: Int -> Int -> Int
aproveitamentoTime ptsOb 54 = (ptsOb/54)*100

--Qual o saldo de gols do time X no campeonato?
sdGols :: Int -> Int -> Int
sdGols golsMarc golsSofr saldoG = golsMarc - golsSofr = saldoG

--Qual o resultado da partida da rodada N do time X no campeonato?
pesqResult :: Int -> Int
pesqResult golsC golsV = golsC && golsV

--Qual o número de pontos do time X no campeonato?
pesqPts :: Int -> Int
pesqPts pts time = time = pts

--Quais os times que estão nas primeiras 3 colocações?



--Quais os times rebaixados?



--Qual a classificação geral do campeonato?