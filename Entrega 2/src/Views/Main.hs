module Main where

import Menu

main :: IO ()
main = do
    Utils.clearScreen
    Menu.menu
