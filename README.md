# Talabia-Chess
Assignment for TCP2201 Obeject-Oriented Analysis and Design 
A chess game with similar rule to international chess without special rules such as *en passent*, *castling* etc. <br> The board is 6 rows and 7 columns which is much smaller than standard chessboard, the colors of the pieces are blue and yellow.<br>
### Rules for playing Talabia Chess:
1. Yellow side start first.
2. Movement for each chess pieces:
   - **Point** (similar to pawn in standard chess): move forward one or two tiles throughout the game, if it reaches the end it will move backwards and vise versa, **unable to capture any pieces**. Cannot skip over other pieces
   - **Plus** (similar to rook in standard chess): move horizontally/vertically with any distance. Cannot skip over other pieces.
   - **Hourglass** (similar to knight in standard chess): move in a 3x2/2x3 (L-shaped). **Can skip over pieces**.
   - **Times** (similar to bishop in standard chess): move in diagonal directions with any distance. Cannot skip over other pieces.
   - **Sun** (similar to king in standard chess): move in any direction but only one tile. **Game will end if the Sun is captured**.
3. After 2 turns (2 yellow move and 2 blue move), the Plus pieces will transform into Times pieces, and Times pieces will transform into Plus pieces.

### Bugs and Errors:
1. The GUI of the Point pieces are not correctly shown, but the pieces still move according to the rules.
