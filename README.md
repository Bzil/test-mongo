# Pre-requirement 

install [mongo](https://www.mongodb.com/docs/upcoming/tutorial/install-mongodb-on-os-x/)  
```
brew tap mongodb/brew
brew update
brew install mongodb-community
```

connect to db then connect to local_db
```
mongosh
use local_db
db.offer.find().pretty(); # find them all
```


Init the project 
Launch `MongoDb` main class with profile `db`
Required one of those param :
- `init`: create all collections
- `cleanup`: delete all collections
- `recreate`: cleanUp + init

Launch the main app
Run `MongoApp` with profile `main`
App will start on port 7001
