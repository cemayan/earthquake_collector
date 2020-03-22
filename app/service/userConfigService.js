
const userConfigService =  {

    saveUserConfig: (payload) => {
       payload.id = undefined; 
       return fetch('http://b65ada9b.ngrok.io/user-config/',{
            headers: {
                'Content-Type': 'application/json'
            },
            method:'POST',
            body: JSON.stringify({
                    json: JSON.stringify(payload),
                    ...payload
            })  
        })
    },
    getUserConfig: (userId) => {
       return fetch('http://b65ada9b.ngrok.io/db-service/user-config/user/' + userId)
       .then(x => x.json())
    },
    deleteFromDb: (id) =>  {
        return fetch('http://b65ada9b.ngrok.io/db-service/user-config/' + id, {
            method: 'DELETE'
        });
    }
}

export default userConfigService;