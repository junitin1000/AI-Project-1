from external_players import get_competitors
from game import Game
p1 = "player1"
p2 = "player2"
time_limit = 100
f_p1, f_p2 = get_competitors(p1, p2, time_limit)
open("move_file", "w").close()
game = Game(f_p1, f_p2, p1_name=p1, p2_name=p2, rand_start=True)
print(8)
game.run()