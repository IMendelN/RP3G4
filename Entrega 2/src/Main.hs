module Main where

--
-- Importando explicitamente o 'Prelude' para
-- remover ambiguidade na palavra 'round'.
--
import qualified Prelude as P

import Views.Menu as Menu ( menu )
import Utils.Utils as Utils ( cls )

main :: P.IO ()
main = do
    Utils.cls
    Menu.menu
