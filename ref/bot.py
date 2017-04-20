import argparse
import json
import logging
import os
import random
import heapq

logger = logging.getLogger()
logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(levelname)-7s - [%(funcName)s] %(message)s')
# uncomment for submission
# logger.disabled = True

ACTIONS = {
	-1: 'DoNothing',
	1: 'MoveUp',
	2: 'MoveLeft',
	3: 'MoveRight',
	4: 'MoveDown',
	5: 'PlaceBomb',
	6: 'TriggerBomb',
}

class WeightedMatrix:
	def __init__(self, size_x, size_y):
		self.width = size_x
		self.height = size_y
		self.weights = {}

	def in_bounds(self, id):
		(x, y) = id
		return 0 <= x < self.width and 0 <= y < self.height

	def neighbors(self, id):
		(x, y) = id
		results = {(x+1, y), (x, y-1), (x-1, y), (x, y+1)}
		results = filter(self.in_bounds, results)
		return results

	def cost(self, from_node, to_node):
		return self.weights.get(to_node,1)

class PriorityQueue:
	def __init__(self):

		self.elements = []
	
	def empty(self):
		return len(self.elements) == 0
	
	def put(self, item, priority):
		heapq.heappush(self.elements, (priority, item))
	
	def get(self):
		return heapq.heappop(self.elements)[1]

def dijkstra_search(matrix, start, goal):
	frontier = PriorityQueue()
	frontier.put(start, 0)
	origin = {}
	movement_cost = {}
	origin[start] = None
	movement_cost[start] = 0

	while not frontier.empty():
		current = frontier.get()
		if current == goal:
			break
		for next in matrix.neighbors(current):
			if matrix.cost(current, next)>700:
				pass
			else:
				new_cost = movement_cost[current] + matrix.cost(current, next)
				if next not in movement_cost:
					movement_cost[next] = new_cost
					priority = new_cost
					frontier.put(next, priority)
					origin[next] = current
	
	return origin, movement_cost

def reconstruct_path(origin, start, goal):
	current = goal
	path = [current]
	while current != start:
		current = origin[current]
		path.append(current)
	path.reverse()
	return path

def Distance(player_x,player_y,dest_x,dest_y):
	return (abs(dest_x-player_x) + abs(dest_y-player_y))

def find_reachable_point(x_size,y_size, col, row, value,visited,ans):
	if (row>0 and col>0 and row<y_size and col<x_size):
		if(not(visited[col][row])):
			visited[col][row] = True
			ans.append((col,row))
			if (value.weights[(col+1,row)] < 500):
				find_reachable_point(x_size,y_size,col+1,row,value,visited,ans)
			if (value.weights[(col-1,row)] < 500):
				find_reachable_point(x_size,y_size,col-1,row,value,visited,ans)
			if (value.weights[(col,row+1)] < 500):
				find_reachable_point(x_size,y_size,col,row+1,value,visited,ans)
			if (value.weights[(col,row-1)] < 500):
				find_reachable_point(x_size,y_size,col,row-1,value,visited,ans)

def safe_place(x_size,y_size,col,row,value,visited,ans,counter,bomb_bag, bomb_radius, player_x,player_y):
	if (row>0 and col>0 and row<y_size and col<x_size):

		if(not(visited[col][row])):
			visited[col][row] = True

			if (counter >= (bomb_bag*3+1 if bomb_bag*3+1<=10 else 10) 
				or Distance(col,row,player_x,player_y) > Distance( (0 if player_x-bomb_radius<0 else player_x),player_y,player_x,player_y)
				or Distance(col,row,player_x,player_y) > Distance( (x_size-1 if player_x+bomb_radius>=x_size else player_x),player_y,player_x,player_y)
				or Distance(col,row,player_x,player_y) > Distance(player_x,(0 if player_y-bomb_radius<0 else player_y),player_x,player_y)	
				or Distance(col,row,player_x,player_y) > Distance(player_x,(y_size-1 if player_y+bomb_radius >=y_size else player_y),player_x,player_y)):
				ans.append((col,row))
			else:
				if (value.weights[(col+1,row)] < 200):
					safe_place(x_size,y_size,col+1,row,value,visited,ans,counter+1,bomb_bag,bomb_radius,player_x,player_y)
				if (value.weights[(col-1,row)] < 200):
					safe_place(x_size,y_size,col-1,row,value,visited,ans,counter+1,bomb_bag,bomb_radius,player_x,player_y)
				if (value.weights[(col,row+1)] < 200):
					safe_place(x_size,y_size,col,row+1,value,visited,ans,counter+1,bomb_bag,bomb_radius,player_x,player_y)
				if (value.weights[(col,row-1)] < 200):
					safe_place(x_size,y_size,col,row-1,value,visited,ans,counter+1,bomb_bag,bomb_radius,player_x,player_y)

def action(x_size,y_size,target_x,target_y,player_x,player_y,bomb_bag,bomb_radius,value,state):
	if (target_x == player_x and target_y == player_y):

		if (bomb_bag > 0):

			foundWall = False
			foundEnemy = False
			for r in range(1,bomb_radius+1):
				if not foundWall:
					if player_x+r < x_size:
						if (state["GameBlocks"][player_x+r][player_y]["Entity"] != None):
							if (state["GameBlocks"][player_x+r][player_y]["Entity"]["$type"] == "Domain.Entities.DestructibleWallEntity, Domain"):
								foundWall = True
					if player_y+r < y_size:
						if (state["GameBlocks"][player_x][player_y+r]["Entity"] != None):
							if (state["GameBlocks"][player_x][player_y+r]["Entity"]["$type"] == "Domain.Entities.DestructibleWallEntity, Domain"):
								foundWall = True
					if player_x-r >= 0:
						if (state["GameBlocks"][player_x-r][player_y]["Entity"] != None):
							if (state["GameBlocks"][player_x-r][player_y]["Entity"]["$type"] == "Domain.Entities.DestructibleWallEntity, Domain"):
								foundWall = True
					if player_y-r >= 0:
						if (state["GameBlocks"][player_x][player_y-r]["Entity"] != None):
							if (state["GameBlocks"][player_x][player_y-r]["Entity"]["$type"] == "Domain.Entities.DestructibleWallEntity, Domain"):
								foundWall = True

				if not foundEnemy:
					if player_x+r < x_size:
						if (state["GameBlocks"][player_x+r][player_y]["Entity"] != None):
							if (state["GameBlocks"][player_x+r][player_y]["Entity"]["$type"] == "Domain.Entities.PlayerEntity, Domain"):
								foundEnemy = True
					if player_y+r < y_size:
						if (state["GameBlocks"][player_x][player_y+r]["Entity"] != None):
							if (state["GameBlocks"][player_x][player_y+r]["Entity"]["$type"] == "Domain.Entities.PlayerEntity, Domain"):
								foundEnemy = True
					if player_x-r >= 0:
						if (state["GameBlocks"][player_x-r][player_y]["Entity"] != None):
							if (state["GameBlocks"][player_x-r][player_y]["Entity"]["$type"] == "Domain.Entities.PlayerEntity, Domain"):
								foundEnemy = True
					if player_y-r >= 0:
						if (state["GameBlocks"][player_x][player_y-r]["Entity"] != None):
							if (state["GameBlocks"][player_x][player_y-r]["Entity"]["$type"] == "Domain.Entities.PlayerEntity, Domain"):
								foundEnemy = True

			visited = [[False for i in range(y_size)] for j in range(x_size)]
			ans = []
			safe_place(x_size,y_size,player_x,player_y,value,visited,ans,0,bomb_bag, bomb_radius, player_x,player_y)

			if (foundWall or foundEnemy) and (state["GameBlocks"][player_x][player_y]["Bomb"] == None and ans != []):
				return 5
			else:
				if (value.weights[(player_x,player_y)] < 300 and bomb_bag == 0):
					return 6
				else:
					## Find other target
					visited = [[False for i in range(y_size)] for j in range(x_size)]
					ans = []
					value.weights[(player_x,player_y)] += 400
					find_reachable_point(x_size,y_size,player_x,player_y,value,visited,ans)
					min = 9999
					for (i,j) in ans:
						if (value.weights[(i,j)] < min):
							min = value.weights[(i,j)]
							target_x = i
							target_y = j
					came_from, cost = dijkstra_search(value, (player_x,player_y), (target_x,target_y))
					path = reconstruct_path(came_from, (player_x,player_y), (target_x,target_y))
					
					if len(path) <= 1:
						return -1
					else:
						next_x, next_y = path[1]
					
						# Target should be 
						if (next_y > player_y):
							return 4
						elif (next_y < player_y):
							return 1
						else:
							if (next_x > player_x):
								return 3
							elif (next_x < player_x):
								return 2
							else:
								return -1

	else:
		came_from, cost = dijkstra_search(value, (player_x,player_y), (target_x,target_y))
		path = reconstruct_path(came_from, (player_x,player_y), (target_x,target_y))
		
		if len(path) <= 1:
			return -1
		else:
			next_x, next_y = path[1]
	
			if (next_y > player_y):
				return 4
			elif (next_y < player_y):
				return 1
			else:
				if (next_x > player_x):
					return 3
				elif (next_x < player_x):
					return 2
				else:
					return -1

def main(player_key, output_path):
	logger.info('Player key: {}'.format(player_key))
	logger.info('Output path: {}'.format(output_path))
	print(player_key)
	with open(os.path.join(output_path, 'state.json'), 'r') as f:
		state = json.load(f)
		logger.info('State: {}'.format(state))

	# Size initialization
	x_size = state["MapWidth"]
	y_size = state["MapHeight"]

	# Matrices Initialization
	visited = [[False for i in range(y_size)] for j in range(x_size)]
	value = WeightedMatrix(x_size,y_size)

	player = ord(player_key) - ord('A')
	player_x = state["RegisteredPlayerEntities"][player]["Location"]["X"]
	player_y = state["RegisteredPlayerEntities"][player]["Location"]["Y"]

	bomb_radius = state["RegisteredPlayerEntities"][player]["BombRadius"]
	bomb_bag = state["RegisteredPlayerEntities"][player]["BombBag"]

	#Initialization of WeightedMatrix
	for i in range(0,x_size):
		for j in range(0,y_size):
			value.weights[(i,j)] = 30
	
	#Value Modifier Distance
	for i in range(0,x_size):
		for j in range(0,y_size):
			if (state["GameBlocks"][i][j]["Entity"] == None):
				value.weights[(i,j)] = 7*Distance(player_x,player_y,i+1,j+1)
				if (state["GameBlocks"][i][j]["PowerUp"] != None):
					value.weights[(i,j)] -= 33

	#Value Modifier Near Wall
	for i in range(0,x_size):
		for j in range(0,y_size):
			if (state["GameBlocks"][i][j]["Entity"] != None):
				if (state["GameBlocks"][i][j]["Entity"]["$type"] == "Domain.Entities.DestructibleWallEntity, Domain" or
				state["GameBlocks"][i][j]["Entity"]["$type"] == "Domain.Entities.IndestructibleWallEntity, Domain"):
					if (state["GameBlocks"][i][j]["Entity"]["$type"] == "Domain.Entities.DestructibleWallEntity, Domain"):
						for r in range(1,bomb_radius+1):
							if (value.weights[(i-1,j)] < 800):
								if (i-r >= 0):
									value.weights[(i-r,j)] -= 9
							if (value.weights[(i,j-1)] < 800):
								if (j-r >= 0):
									value.weights[(i,j-r)] -= 9
							if (value.weights[(i+1,j)] < 800):
								if (i+r < x_size):
									value.weights[(i+r,j)] -= 9
							if (value.weights[(i,j+1)] < 800):
								if (j+r < y_size):
									value.weights[(i,j+r)] -= 9
						value.weights[(i,j)] += 1500
					else:
						value.weights[(i,j)] += 2000
				elif (state["GameBlocks"][i][j]["Entity"]["$type"] == "Domain.Entities.PlayerEntity, Domain"):	
					if (state["GameBlocks"][i][j]["Entity"]["Key"] == player_key):
						value.weights[(i,j)] -= 30
					else:
						value.weights[(i,j)] += 1500

		
	# Change Value if any bomb in radius 
	for i in range(0,x_size):
		for j in range(0,y_size):
			if (state["GameBlocks"][i][j]["Bomb"] == None):
				pass
			else:
				bomb_radius = state["GameBlocks"][i][j]["Bomb"]["BombRadius"]
				bomb_timer = state["GameBlocks"][i][j]["Bomb"]["BombTimer"]
				exploding = state["GameBlocks"][i][j]["Exploding"]
				bomb_owner = state["GameBlocks"][i][j]["Bomb"]["Owner"]["Key"]
				# Added anti-suicide bomb escape route
				if (bomb_owner == player_key):
					bomb_bag -= 1

				if (bomb_timer <= 2 or exploding):
					for direction in {"up","right","down","left"}:
						blocked = False
						for r in range(1,bomb_radius+1):
							if direction == "up":
								if (j-r >= 0):
									if (value.weights[(i,j-r)] > 1400):
										blocked = True
									if not blocked:
										value.weights[(i,j-r)] = 750
							if direction == "right":
								if (i+r < x_size):
									if (value.weights[(i+r,j)] > 1400):
										blocked = True
									if not blocked:
										value.weights[(i+r,j)] = 750
							if direction == "down":
								if (j+r < y_size):
									if (value.weights[(i,j+r)] > 1400):
										blocked = True
									if not blocked:
										value.weights[(i,j+r)] = 750
							if direction == "left":
								if (i-r >= 0):
									if (value.weights[(i-r,j)] > 1400):
										blocked = True
									if not blocked:
										value.weights[(i-r,j)] = 750	
					value.weights[(i,j)] = 1200
				else:
					enemyFound = False
					for direction in {"up","right","down","left"}:
						blocked = False
						#Trigger detection mechanism
						if (bomb_owner != player_key):
							if (state["GameBlocks"][i][j]["Entity"] != None):
								if (state["GameBlocks"][i][j]["Entity"]["$type"] == "Domain.Entities.PlayerEntity, Domain"):
									if (state["GameBlocks"][i][j]["Entity"]["Key"] == bomb_owner):
										enemyFound = True
							#Find if owner is in range
							if not enemyFound:
								for r in range(1,bomb_radius+1):
									if direction == "up":
										if (j-r >= 0):
											if (state["GameBlocks"][i][j-r]["Entity"] != None):
												if (state["GameBlocks"][i][j-r]["Entity"]["$type"] == "Domain.Entities.PlayerEntity, Domain"):
													if (state["GameBlocks"][i][j-r]["Entity"]["Key"] == bomb_owner):
														enemyFound = True

									if direction == "right":
										if (i+r < x_size):
											if (state["GameBlocks"][i+r][j]["Entity"] != None):
												if (state["GameBlocks"][i+r][j]["Entity"]["$type"] == "Domain.Entities.PlayerEntity, Domain"):
													if (state["GameBlocks"][i+r][j]["Entity"]["Key"] == bomb_owner):
														enemyFound = True
									if direction == "down":
										if (j+r < y_size):
											if (state["GameBlocks"][i][j+r]["Entity"] != None):
												if (state["GameBlocks"][i][j+r]["Entity"]["$type"] == "Domain.Entities.PlayerEntity, Domain"):
													if (state["GameBlocks"][i][j+r]["Entity"]["Key"] == bomb_owner):
														enemyFound = True
									if direction == "left":
										if (i-r >= 0):
											if (state["GameBlocks"][i-r][j]["Entity"] != None):
												if (state["GameBlocks"][i-r][j]["Entity"]["$type"] == "Domain.Entities.PlayerEntity, Domain"):
													if (state["GameBlocks"][i-r][j]["Entity"]["Key"] == bomb_owner):
														enemyFound = True
						if (enemyFound):
							chanceTriggered = False
						else:
							if (bomb_owner != player_key):
								chanceTriggered = True
							else:
								chanceTriggered = False

					for direction in {"up","right","down","left"}:
						blocked = False
						for r in range(1,bomb_radius+1):
							if direction == "up":
								if (j-r >= 0):
									if (value.weights[(i,j-r)] > 1400):
										blocked = True
									if not blocked and value.weights[(i,j-r)] < 500:
										if (chanceTriggered):
											value.weights[(i,j-r)] = 750
										else:
											value.weights[(i,j-r)] = 300

							if direction == "right":
								if (i+r < x_size):
									if (value.weights[(i+r,j)] > 1400):
										blocked = True
									if not blocked and value.weights[(i+r,j)] < 500:
										if (chanceTriggered):
											value.weights[(i+r,j)] = 750
										else:
											value.weights[(i+r,j)] = 300
							if direction == "down":
								if (j+r < y_size):
									if (value.weights[(i,j+r)] > 1400):
										blocked = True
									if not blocked and value.weights[(i,j+r)] < 500:
										if (chanceTriggered):
											value.weights[(i,j+r)] = 750
										else:
											value.weights[(i,j+r)] = 300
							if direction == "left":
								if (i-r >= 0):
									if (value.weights[(i-r,j)] > 1400):
										blocked = True
									if not blocked and value.weights[(i-r,j)] < 500:
										if (chanceTriggered):
											value.weights[(i-r,j)] = 750
										else:
											value.weights[(i-r,j)] = 300	
					for direction in {"up","right","down","left"}:
						blocked = False
						for r in range(1,bomb_radius+1):
							if direction == "up":
								if (j-r >= 0):
									if (value.weights[(i,j-r)] > 1400):
										blocked = True
									if not blocked:
										value.weights[(i,j-r)] += 10*(10-bomb_timer)
							if direction == "right":
								if (i+r < x_size):
									if (value.weights[(i+r,j)] > 1400):
										blocked = True
									if not blocked:
										value.weights[(i+r,j)] += 10*(10-bomb_timer)
							if direction == "down":
								if (j+r < y_size):
									if (value.weights[(i,j+r)] > 1400):
										blocked = True
									if not blocked:
										value.weights[(i,j+r)] += 10*(10-bomb_timer)
							if direction == "left":
								if (i-r >= 0):
									if (value.weights[(i-r,j)] > 1400):
										blocked = True
									if not blocked:
										value.weights[(i-r,j)] += 10*(10-bomb_timer)	
					value.weights[(i,j)] = 1200

				# Death tunnel avoidance
				for direction in {"up","right","down","left"}:
					dead_end = True
					blocked = False
					radius = 1
					for r in range(1,bomb_radius+2):
						if direction == "up":
							if (j-r >= 0):
								if (value.weights[(i,j-r)] > 1400):
									blocked = True
								if not blocked:
									if value.weights[(i-1,j-r)]<=200 or value.weights[(i+1,j-r)]<=200 or value.weights[(i,j-r)]<=200:
										dead_end = False
										radius = r
						if direction == "right":
							if (i+r < x_size):
								if (value.weights[(i+r,j)] > 1400):
									blocked = True
								if not blocked:
									if value.weights[(i+r,j-1)]<=200 or value.weights[(i+r,j+1)]<=200 or value.weights[(i+r,j)]<=200:
										dead_end = False
										radius = r
						if direction == "down":
							if (j+r < y_size):
								if (value.weights[(i,j+r)] > 1400):
									blocked = True
								if not blocked:
									if value.weights[(i-1,j+r)]<=200 or value.weights[(i+1,j+r)]<=200 or value.weights[(i,j+r)]<=200:	
										dead_end = False
										radius = r
						if direction == "left":
							if (i-r >= 0):
								if (value.weights[(i-r,j)] > 1400):
									blocked = True
								if not blocked:
									if value.weights[(i-r,j-1)]<=200 or value.weights[(i-r,j+1)]<=200 or value.weights[(i-r,j)]<=200:
										dead_end = False
										radius = r		
					if dead_end:
						for r in range(1, radius+1):
							if direction == "up":
								if (j-r >= 0):
									value.weights[(i,j-r)] += 1000
							if direction == "right":
								if (i+r < x_size):
									value.weights[(i+r,j)] += 1000
							if direction == "down":
								if (j+r < y_size):
									value.weights[(i,j+r)] += 1000
							if direction == "left":
								if (i-r >= 0):
									value.weights[(i-r,j)] += 1000

	#Greedy by lowest value
	target_x = 0
	target_y = 0

	player_x -= 1
	player_y -= 1
	
	ans = []

	find_reachable_point(x_size,y_size,player_x,player_y,value,visited,ans)

	if (len(ans)<50):
		for (i,j) in ans:
			value.weights[(i,j)] -= 1

	min = 9999
	for (i,j) in ans:
		if (value.weights[(i,j)] < min):
			min = value.weights[(i,j)]
			target_x = i
			target_y = j

	solution = action(x_size,y_size,target_x,target_y,player_x,player_y,bomb_bag,bomb_radius,value,state)
	logger.info('Action: {}'.format(ACTIONS[solution]))

	with open(os.path.join(output_path, 'move.txt'), 'w') as f:
		f.write('{}\n'.format(solution))

if __name__ == '__main__':
	parser = argparse.ArgumentParser()
	parser.add_argument('player_key', nargs='?')
	parser.add_argument('output_path', nargs='?', default=os.getcwd())
	args = parser.parse_args()

	assert(os.path.isdir(args.output_path))

	main(args.player_key, args.output_path)
