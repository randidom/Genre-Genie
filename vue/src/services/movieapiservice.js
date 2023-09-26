import axios from 'axios';

export default {

getTrending(){
    return axios.get('https://api.themoviedb.org/3/trending/all/day?api_key=ea5f97c7d5398eac0c2b48ed86ab30dc')
}

}