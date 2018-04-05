var app = angular.module('newsApp', []);

app.controller('newsCtrl', function ($scope) {
    /*$scope.friends = [
        {
            'id': 'c4f63463-beb9-42c7-8921-7d3184845689',
            'name': 'Vasja',
            'pathAvatarImage': '11.jpg'
        },
        {
            'id': '36fe8f70-3287-4521-b00f-807682ab8204',
            'name': 'Dima',
            'pathAvatarImage': '11.jpg'
        },
        {
            'id': 'bfae92d5-84c1-46ec-afe4-57a44bfac85e',
            'name': 'Olga',
            'pathAvatarImage': '11.jpg'
        }];*/
});

app.controller('postCtrl', function ($scope, $http) {

    /*
        $http.post("http://127.0.0.1:8080/news/getnews?id=57c49497-918c-4ece-81d9-629d11c5ad6b")
            .then(function (response) {
                alert(response);
                $scope.posts = response.data;
            });
    */

    /*
        $scope.defaultUserId = '57c49497-918c-4ece-81d9-629d11c5ad6b';
        alert('id: ', defaultUserId);
        $scope.getPosts = function (userId) {
            $http.post("http://127.0.0.1:8080/news/getnews?id=11112222")
                .then(function(response) {
                    $scope.posts = response.data;
                });
    */

    $scope.UserId = '57c49497-918c-4ece-81d9-629d11c5ad6b';

    $scope.getPosts = function (userId) {
        $http({
            method: "POST",
            url: "http://127.0.0.1:8080/news/getnews",
            params: {id: userId}
        }).then(function mySuccess(response) {
            $scope.posts = response.data;
        }, function myError(response) {
            alert(response.statusText);
        });
    };
    $scope.getFriends = function (userId) {
        $http({
            method: "POST",
            url: "http://127.0.0.1:8080/user/getfriends",
            params: {id: userId}
        }).then(function mySuccess(response) {
            $scope.friends = response.data;
            console.log($scope.friends);
        }, function myError(response) {
            alert(response.statusText);
        });
    };
    $scope.getFriends($scope.UserId);
    $scope.getPosts($scope.UserId);

    /*        $scope.posts = [
                {
                    postId: '8acd4c77-1472-49a9-9ce8-7a863166b4a6',
                    authorId: '57c49497-918c-4ece-81d9-629d11c5ad6b',
                    postOwner: 'owner 1',
                    postTitle: 'Topic 1',
                    postDate: '2018-03-05 16:11:14',
                    postText: 'Go all to drinking party! part 1',
                    postComment: [
                        '2457258472457',
                        '9058469548668',
                        '2394782374244',
                        '3458235943533',
                        '3455345543545'
                    ],
                    postLike: [
                        '42554-345345-54345',
                        '56757-664566-34534'
                    ],
                    postFavorite: 'true',
                },
                {
                    postId: '22947654678957468',
                    postOwner: 'owner 2',
                    postTitle: 'Topic 2',
                    postDate: '23.02.2018 13:23',
                    postText: 'Go all to drinking party! part 2',
                    postComment: [
                        '3457258472457',
                        '3058469548668',
                        '3458235943533',
                        '3455345543545'
                    ],
                    postLike: [
                        '32554-345345-54345',
                        '36757-664566-34534'
                    ],
                    postFavorite: 'false',
                },
                {
                    postId: '33347654678957468',
                    postOwner: 'owner 3',
                    postTitle: 'Topic 3',
                    postDate: '24.02.2018 13:23',
                    postText: 'Go all to drinking party! part 3',
                    postComment: [
                        '4457258472457',
                        '4058469548668',
                        '4394782374244'
                    ],
                    postLike: [
                        '46757-664566-34534'
                    ],
                    postFavorite: 'true',
                },
            ];*/
});

app.component('newsPost', {
    //controller: '',
    controllerAs: 'ctrl',
    templateUrl: 'components/post/post.html',
    bindings: {
        post: '='
    }
});

