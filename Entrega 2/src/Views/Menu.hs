module Views.Menu where

import System.IO ( hFlush, stdout )

import Utils.Utils as Utils
import Championship.ReadFile as File
import Championship.Structures
import Championship.Manipulate ( getMatches, getWinnerByRound )

--
-- Menu principal.
--
menu :: IO ()
menu = do
    putStrLn (purple ++ "[BEM-VINDO AO CAMPEONATO UNIPAMPA]\n" ++ reset) 
    putStrLn "Menu de opções:\n"
    putStrLn "\t1 - Visualizar o pódio"
    putStrLn "\t2 - Visualizar times rebaixados"
    putStrLn "\t3 - Visualizar a classificação geral"
    putStrLn "\t4 - Visualizar opções por time"
    putStrLn "\t5 - Visualizar por rodada"
    putStrLn "\t0 - Sair"
    putStr (yellow ++ "\nDigite uma das opções acima: " ++ reset)
    hFlush stdout
    option <- getLine
    menuOptions option

--
-- Menu de escolha para o menu principal.
--
menuOptions :: String -> IO ()
menuOptions option = do
    case option of
        "1" -> do
            Utils.cls
            putStrLn "I'm not doing anything... yet."
        "2" -> do
            Utils.cls
            menu
        "3" -> do
            Utils.cls
            menu
        "4" -> do
            Utils.cls
            menu
        "5" -> do
            -- Apenas para teste.
            putStr (yellow ++ "Digite a rodada: " ++ reset)
            hFlush stdout
            round <- getLine
            matches <- getMatches
            let winner = getWinnerByRound (read round) matches
            let show | winner == "Empate" = putStrLn "Esta rodada possui empate."
                     | otherwise = putStrLn ("O time vencedor desta jogada é: " ++ winner)
            show
            -- Fim do teste.
        "0" -> do
            putStrLn (blue ++ "\nPrograma encerrado." ++ reset)
        _ -> do
            optionInvalid
            menu

--
-- Menu para visualizar opções de times individualmente.
--
allTeams :: IO ()
allTeams = do
    Utils.cls
    putStrLn (purple ++ "[OPÇÕES POR TIME]\n" ++ reset) 
    putStrLn "Menu de opções:\n"
    putStrLn "\t1 - Visualizar vitórias, derrotas e empates"
    putStrLn "\t2 - Classificação do time"
    putStrLn "\t3 - Aproveitamento do time"
    putStrLn "\t4 - Visualizar opções por time"
    putStrLn "\t0 - Sair"
    putStr (yellow ++ "\nDigite uma das opções acima: " ++ reset)
    hFlush stdout
    option <- getLine
    menuOptions option

--
-- Mensagem de erro para opções inválidas de menu.
--
optionInvalid :: IO ()
optionInvalid = do
    Utils.cls
    putStrLn (red ++ "* Por favor, digite uma opção válida.\n" ++ reset)