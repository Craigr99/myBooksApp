# myBooksApp
Mobile app for myBooks using Kotlin

## Project setup - backend
```
The backend is required for this application. The backend data is retrieved from a Laravel API 
```
### Installation steps
```
1. Clone the backend repo: https://github.com/Craigr99/myBooks.git
2. cd myBooks
3. run composer install
4. run npm install
5. Rename the .env.example to .env
6. Set up credentials in .env file:
    DB_HOST=127.0.0.1
    DB_PORT=3306
    DB_DATABASE=mybooks
    DB_USERNAME=root
    DB_PASSWORD=
7. Create mybooks database in phpmyadmin
8. run php artisan key:generate
9. run php artisan migrate --seed
```
