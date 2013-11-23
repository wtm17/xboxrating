modules = {
    angularJsAndBootstrapAndMore{
        defaultBundle(false) //bundle option should be turned on in INT/PROD
        resource url: 'https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js', disposition: 'defer'
        resource url: 'https://ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular.min.js', disposition: 'defer'
        resource url: 'https://ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular-resource.min.js', disposition: 'defer'
        resource url: 'https://ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular-cookies.min.js', disposition: 'defer'
        resource url: 'https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js', disposition: 'defer'

    }
    application {
        defaultBundle(false)
        def cacheBust = System.currentTimeMillis()
        resource url: 'angularjs/app.js?d='+ cacheBust, disposition: 'defer'
        resource url: 'angularjs/games/gamesCtrl.js?d='+ cacheBust, disposition: 'defer'
        resource url: 'angularjs/games/gamesService.js?d='+ cacheBust, disposition: 'defer'
    }
    styles {
        resource url: 'https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css', attrs: [rel: "stylesheet", type: 'css'], disposition: 'head'
        resource url: 'https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css', attrs: [rel: "stylesheet", type: 'css'], disposition: 'head'
        resource url: 'http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css', attrs: [rel: "stylesheet", type: 'css'], disposition: 'head'
        resource url: [dir: 'css', file: 'style.css'], attrs: [rel: "stylesheet", type: 'css'], disposition: 'head'
    }
}