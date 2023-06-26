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
