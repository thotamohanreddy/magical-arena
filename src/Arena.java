

public class Arena {
    private Player playerA;
    private Player playerB;

    public Arena(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public void startBattle() {
        Player attacker, defender;
        if (playerA.getHealth() < playerB.getHealth()) {
            attacker = playerA;
            defender = playerB;
        } else {
            attacker = playerB;
            defender = playerA;
        }

        while (playerA.isAlive() && playerB.isAlive()) {
            takeTurn(attacker, defender);
            if (!defender.isAlive()) break;
            takeTurn(defender, attacker);
        }

        if (playerA.isAlive()) {
            System.out.println("Player A wins!");
        } else {
            System.out.println("Player B wins!");
        }
    }

    private void takeTurn(Player attacker, Player defender) {
        int attackRoll = attacker.rollDice();
        int defendRoll = defender.rollDice();
        int attackDamage = attacker.getAttack() * attackRoll;
        int defendValue = defender.getStrength() * defendRoll;
        int damageDealt = Math.max(0, attackDamage - defendValue);
        defender.setHealth(defender.getHealth() - damageDealt);

        System.out.printf("%s attacks %s: attack roll = %d, defend roll = %d, damage dealt = %d, defender's health = %d\n",
            attacker == playerA ? "Player A" : "Player B",
            defender == playerA ? "Player A" : "Player B",
            attackRoll, defendRoll, damageDealt, defender.getHealth());
    }
}
