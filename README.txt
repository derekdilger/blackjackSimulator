This repo is from scratch by Derek Dilger. It simulates blackjack side bets at Capital Casino to find their expected value.

Q: What is the expected value of the Red Flex Bet?
A: For every $100 wagered, $95.346 are returned to the player.

Q: What is the expected value of the Buster Bet?
A: For every $100 wagered, $94.623 are returned to the player.

Red flex (RF) payout schedule: 
num_dealer_reds:    2   3   4   5   6   7
payout_odds    :    2   3   8   50  100 250

Buster Bet (BB) payout schedule: 
num_dealer_cards:   3   4   5   6   7   8
payout_odds     :   2   2   4   12  100 250




Here's the raw simulation result:

this program took 31056 seconds
total hands dealt: 44000000000
total dealer busts: 12572029805 or 28.572795011363635%
total redflex wins: 10964578517 or 24.919496629545456% (theoretical value 24.92% exactly)
total trifectas (subset of lines 2 and 3) : 3134195639 or 7.123171906818182%
total balanced trifectas (subset of 4) : 1213273854 or 2.7574405772727273%

3cardBB:  7604318844 17.282543%
4cardBB:  3937468217  8.948791%
5cardBB:  903586427  2.053606%
6cardBB:  116634251  0.265078%
7cardBB:    9493678  0.021577%
8cardBB:     509975  0.001159%
9cardBB:      17976  0.000041%
10cardBB:       433  0.000001%
11cardBB:         4  0.000000%
12cardBB:         0  0.000000%
13cardBB:         0  0.000000%

2cardRF:  7349774721 16.704033%
3cardRF:  3019199037  6.861816%
4cardRF:  540908566  1.229338%
5cardRF:   51650053  0.117386%
6cardRF:    2937689  0.006677%
7cardRF:     105876  0.000241%
8cardRF:       2541  0.000006%
9cardRF:         33  0.000000%
10cardRF:         1  0.000000%
11cardRF:         0  0.000000%
12cardRF:         0  0.000000%
13cardRF:         0  0.000000%

balancedTrifecta3:  941679646  2.140181%
balancedTrifecta4:  242620630  0.551411%
balancedTrifecta5:   27235071  0.061898%
balancedTrifecta6:    1673868  0.003804%
balancedTrifecta7:      63045  0.000143%
balancedTrifecta8:       1572  0.000004%
balancedTrifecta9:         22  0.000000%
balancedTrifecta10:         0  0.000000%
balancedTrifecta11:         0  0.000000%
balancedTrifecta12:         0  0.000000%
balancedTrifecta13:         0  0.000000%


   
