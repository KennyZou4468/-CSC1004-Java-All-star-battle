# All-star-battle

This is a documentation and tutorial that will help you to know
what is this program is.
Under the folder :src/main/java/com/kenny/AllStarBattle, there are main
Classes and packages. Among all, AllStarBattleApp is the main class to
run this game.

1. AllStarBattleApp: we firstly initialize the game, like Width and Height,
Title... Then we customize our scene by creating SceneFactory. By override onPreInit,
we can add music to the game. There are two start level methods: Start normal level and 
hidden level, which will create enemy for specific time interval.They are quite similar
and i think they can be combined into one function in the future. Initinput allows us
to control our player to move.In initPhysics function, we add many collisionhandler to handle
the collision. At lase, using a main function to launch the game.

2. AllStarBattleFactory: in this class, we store our entities information and properties
and use them in the main class by calling "Spawn ..."  Every entity can add properties
like HP,Gametype, level, which entity it can have collision and what effect it will have.

3. Config ,Dir, Gametype, PropsType, PropsType_forHiddenLevel: They are list class, just to simplify the codes and make it clear.

4. UI package: Different ui that would apply in the game, like loading scene, main_menu scene, success Scene...

5. Effect package: Used for some props' effects. Godeffect will make player maintain current HP. PortalEffect will make player go through wall.

6. db package: Mainly used to connect to database MYSQL. It can verify username and password.

7. components package: It will provide different component and properties to entities, like how they would move, how do they shoot, restore hp after 
geting a hp package...

8. collisions package: Probably the most important things in game. They are collision handlers, which will react to collision. This enables us to
make the game reliable and enjoyable. They will detect bullet and other objects collision and decide whether objects will be ruined, and not allow 
two entity keep moving if they meet each other.