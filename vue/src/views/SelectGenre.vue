
    <template>
        <div class="container">
            <div class="left-section">
                <h2>Select Different Genres</h2>
                <ul>
                    <li v-for="genre in genres" :key="genre">
                        <label>
                            <input type="checkbox" :value="genre" v-model="selectedGenres">
                            {{ genre }}
                        </label>
                    </li>
                </ul>
            </div>
            <div class="right-section">
                <div class="genre" v-for="genre in selectedGenres" :key="genre">{{ genre }}</div>
            </div>
        </div>
   </template>
