package osp.leobert.android.github.repo.api

/**
 * <p><b>Package:</b> osp.leobert.android.github.repo.api </p>
 * <p><b>Classname:</b> GithubRepoApi </p>
 * Created by leobert on 2020/9/18.
 */
interface GithubRepoApi {
    //Lists public repositories for the specified user.
    //
    //get /users/{username}/repos
    //
    //Parameters
    //Name 	Type 	In 	Description
    //accept 	string 	header
    //
    //Setting to application/vnd.github.v3+json is recommended
    //See preview notice.
    //username 	string 	path
    //type 	string 	query
    //
    //Can be one of all, owner, member.
    //sort 	string 	query
    //
    //Can be one of created, updated, pushed, full_name.
    //direction 	string 	query
    //
    //Can be one of asc or desc. Default: asc when using full_name, otherwise desc
    //per_page 	integer 	query
    //
    //Results per page (max 100)
    //page 	integer 	query
    //
    //Page number of the results to fetch.
    suspend fun reposOfUser()
}