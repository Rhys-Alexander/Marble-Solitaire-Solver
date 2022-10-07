coords = []
poss_moves = []
for i in range(7):
    for j in range(7):
        coords.append((i, j))
coords = [
    cood
    for cood in coords
    if cood[0] not in (0, 1, 5, 6) or cood[1] not in (0, 1, 5, 6)
]
for cood in coords:
    if (cood[0] + 2, cood[1]) in coords:
        poss_moves.append(
            (
                coords.index(cood),
                coords.index((cood[0] + 1, cood[1])),
                coords.index((cood[0] + 2, cood[1])),
            )
        )
    if (cood[0] - 2, cood[1]) in coords:
        poss_moves.append(
            (
                coords.index(cood),
                coords.index((cood[0] - 1, cood[1])),
                coords.index((cood[0] - 2, cood[1])),
            )
        )
    if (cood[0], cood[1] + 2) in coords:
        poss_moves.append(
            (
                coords.index(cood),
                coords.index((cood[0], cood[1] + 1)),
                coords.index((cood[0], cood[1] + 2)),
            )
        )
    if (cood[0], cood[1] - 2) in coords:
        poss_moves.append(
            (
                coords.index(cood),
                coords.index((cood[0], cood[1] - 1)),
                coords.index((cood[0], cood[1] - 2)),
            )
        )

java_str = ", ".join(f"new Move{move}" for move in poss_moves)
print(java_str)
