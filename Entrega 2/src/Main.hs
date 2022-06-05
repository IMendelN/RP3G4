module Main where

import Views.Menu as Menu ( menu )
import Utils.Utils as Utils ( clearScreen )

main :: IO ()
main = do
    Utils.clearScreen
    Menu.menu
