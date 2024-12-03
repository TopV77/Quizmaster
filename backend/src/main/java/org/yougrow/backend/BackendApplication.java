package org.yougrow.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yougrow.backend.entities.Category;
import org.yougrow.backend.entities.Question;
import org.yougrow.backend.entities.Quiz;
import org.yougrow.backend.entities.Tag;
import org.yougrow.backend.services.CategoryService;
import org.yougrow.backend.services.QuestionService;
import org.yougrow.backend.services.QuizService;
import org.yougrow.backend.services.TagService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataInitializer(CategoryService categoryService,
                                             PasswordEncoder passwordEncoder,
                                             QuizService quizService,
                                             QuestionService questionService,
                                             TagService tagService) {
        return args -> {

            boolean hasNoContent = questionService.getAllQuestionsShort().isEmpty();

            if (hasNoContent) {
                String encodedPassword = passwordEncoder.encode("quizmaster");

                // Categories
                List<Category> categories = Arrays.asList(
                        new Category("Science", "'Science is organized knowledge'. – Herbert Spencer", encodedPassword, "#2C3E50"),
                        new Category("History", "Explore key moments and figures from history. Challenge your knowledge and uncover fascinating facts!", encodedPassword, "#2E2E3A"),
                        new Category("Sports", "Test your knowledge of sports, athletes, and iconic moments from around the world.", encodedPassword, "#00008B"),
                        new Category("Technology", "Discover the coolest tech innovations and trends shaping the future.", encodedPassword, "#013220"),
                        new Category("Entertainment", "This category dives into the world of movies, music, and pop culture, testing your knowledge of iconic moments and famous personalities.", encodedPassword, "#A34700")
                );
                categories.forEach(categoryService::addCategory);

                // Tags
                List<Tag> tags = Arrays.asList(
                        new Tag("Science"),
                        new Tag("History"),
                        new Tag("Sports"),
                        new Tag("Math"),
                        new Tag("Technology"),
                        new Tag("Entertainment"),
                        new Tag("Physics"),
                        new Tag("Biology"),
                        new Tag("Movies"),
                        new Tag("Olympics"),
                        new Tag("Trivia"),
                        new Tag("Programming"),
                        new Tag("Space"),
                        new Tag("Innovation"),
                        new Tag("Games")
                );
                tags.forEach(tagService::addTag);

                // Quizzes
                List<Quiz> quizzes = Arrays.asList(
                        new Quiz("General Science Quiz", "Test your knowledge of general science.", encodedPassword, true),
                        new Quiz("History Quiz", null, encodedPassword, false),
                        new Quiz("Sports Quiz", "Test your sports knowledge.", encodedPassword, true),
                        new Quiz("Tech Innovations Quiz", "How much do you know about technology?", encodedPassword, false),
                        new Quiz("Movie Trivia Quiz", "Guess the movies based on the clues.", encodedPassword, true),
                        new Quiz("Computer Game Quiz", "Computer Questions", encodedPassword, false),
                        new Quiz("Anime Quiz", "Questions about Anime", encodedPassword, false),
                        new Quiz("History Quiz  2.0", "Quiz about historic facts", encodedPassword, false),
                        new Quiz("Music Quiz", "Quiz about music", encodedPassword, false),
                        new Quiz("Politics Quiz", "Quiz about politics", encodedPassword, false),
                        new Quiz("General Quiz", "Quiz about random facts", encodedPassword, false),
                        new Quiz("School knowledge Quiz", "Quiz about school knowledge", encodedPassword, false),
                        new Quiz("Netflix Quiz", "Quiz about Netflix", encodedPassword, false)
                );

                // Assign each quiz to a category
                quizzes.get(0).setCategory(categories.get(0)); // Science
                quizzes.get(1).setCategory(categories.get(1)); // History
                quizzes.get(2).setCategory(categories.get(2)); // Sports
                quizzes.get(3).setCategory(categories.get(3)); // Technology
                quizzes.get(4).setCategory(categories.get(4)); // Entertainment

                quizzes.get(5).setCategory(categories.get(4)); //Entertainment
                quizzes.get(6).setCategory(categories.get(4)); //Entertainment
                quizzes.get(7).setCategory(categories.get(1)); //History
                quizzes.get(8).setCategory(categories.get(4)); //Entertainment
                quizzes.get(9).setCategory(categories.get(4)); //Entertainment
                quizzes.get(10).setCategory(categories.get(4)); //Entertainment
                quizzes.get(11).setCategory(categories.get(0)); // Science
                quizzes.get(12).setCategory(categories.get(4)); //Entertainment
                quizzes.forEach(quizService::addQuiz);

                // Questions
                List<Question> questions = new ArrayList<>();

                // Science Questions
                questions.addAll(Arrays.asList(
                        new Question("What is the formula for water?", encodedPassword, "Text Input", "H2O", null, null, null, null, true),
                        new Question("Which planet is known as the Red Planet?", encodedPassword, "Single Choice", "B", "Earth", "Mars", "Venus", "Jupiter", true),
                        new Question("What is the speed of light?", encodedPassword, "Text Input", "299792458 m/s", null, null, null, null, true),
                        new Question("Which of the following are noble gases?", encodedPassword, "Multiple Choice", "A; C", "Helium", "Oxygen", "Neon", "Carbon", false),
                        new Question("What is the atomic number of Hydrogen?", encodedPassword, "Single Choice", "A", "1", "2", "3", "4", false)
                ));

                // History Questions
                questions.addAll(Arrays.asList(
                        new Question("Who was the first president of the United States?", encodedPassword, "Single Choice", "A", "George Washington", "Abraham Lincoln", "Thomas Jefferson", "John Adams", true),
                        new Question("Which year marked the fall of the Berlin Wall?", encodedPassword, "Text Input", "1989", null, null, null, null, true),
                        new Question("Which of these are ancient civilizations?", encodedPassword, "Multiple Choice", "A; B", "Roman", "Greek", "American", "Modern", false),
                        new Question("In which year did World War I begin?", encodedPassword, "Single Choice", "B", "1912", "1914", "1918", "1920", false),
                        new Question("Who was the leader of the Soviet Union during World War II?", encodedPassword, "Text Input", "Joseph Stalin", null, null, null, null, true)
                ));

                // Sports Questions
                questions.addAll(Arrays.asList(
                        new Question("Who won the FIFA World Cup in 2018?", encodedPassword, "Single Choice", "A", "France", "Germany", "Brazil", "Argentina", true),
                        new Question("How many players are there in a football team?", encodedPassword, "Single Choice", "A", "11", "12", "13", "10", false),
                        new Question("What is the name of the oldest tennis tournament?", encodedPassword, "Text Input", "Wimbledon", null, null, null, null, true),
                        new Question("Which city hosted the 2012 Summer Olympics?", encodedPassword, "Single Choice", "B", "Beijing", "London", "Rio de Janeiro", "Tokyo", false),
                        new Question("Who is the fastest man in the world?", encodedPassword, "Text Input", "Usain Bolt", null, null, null, null, true)
                ));

                // Tech Questions
                questions.addAll(Arrays.asList(
                        new Question("Who invented the World Wide Web?", encodedPassword, "Text Input", "Tim Berners-Lee", null, null, null, null, true),
                        new Question("What does HTTP stand for?", encodedPassword, "Text Input", "HyperText Transfer Protocol", null, null, null, null, false),
                        new Question("Which company developed the Java programming language?", encodedPassword, "Single Choice", "A", "Sun Microsystems", "Microsoft", "Apple", "Google", true),
                        new Question("Which of the following are programming languages?", encodedPassword, "Multiple Choice", "A; C; D", "Python", "HTML", "Java", "C++", false),
                        new Question("What is the binary representation of the number 5?", encodedPassword, "Text Input", "101", null, null, null, null, true)
                ));

                // Games Questions
                questions.addAll(Arrays.asList(
                        new Question("Which video game franchise features characters named Link and Zelda?", encodedPassword, "Single Choice", "B", "Final Fantasy", "Zelda", "The Witcher", "Halo", false),
                        new Question("What is the name of the main character in \"The Witcher\" series?", encodedPassword, "Single Choice", "A", "Geralt of Rivia", "Kratos", "Lara Croft", "Master Chief", false),
                        new Question("Which popular battle royale game was developed by Epic Games?", encodedPassword, "Single Choice", "A", "Fortnite", "Apex Legends", "PUBG", "Call of Duty", false),
                        new Question("In which game do players fight against creepers and ender dragons?", encodedPassword, "Single Choice", "B", "Roblox", "Minecraft", "Terraria", "ARK: Survival Evolved", false),
                        new Question("Which video game takes place in the fictional land of Skyrim?", encodedPassword, "Single Choice", "A", "Elder Scrolls V", "The Witcher 3", "Dark Souls", "Dragon Age", false),
                        new Question("What color is Pac-Man?", encodedPassword, "Single Choice", "A", "Yellow", "Blue", "Green", "Red", false),
                        new Question("Which company created the Xbox console?", encodedPassword, "Single Choice", "C", "Sony", "Nintendo", "Microsoft", "Sega", false),
                        new Question("What is the name of the main character in the \"Halo\" series?", encodedPassword, "Single Choice", "C", "Commander Shepard", "Nathan Drake", "Master Chief", "Marcus Fenix", false),
                        new Question("Which game features a hidden blade and a group called the Assassins?", encodedPassword, "Single Choice", "B", "Dark Souls", "Assassin's Creed", "God of War", "Dishonored", false),
                        new Question("Which multiplayer online battle arena game is developed by Riot Games?", encodedPassword, "Single Choice", "B", "Dota 2", "League of Legends", "Valorant", "Overwatch", false),
                        new Question("Which game involves catching virtual creatures using Poké Balls?", encodedPassword, "Single Choice", "C", "Digimon", "Animal Crossing", "Pokémon", "Harvest Moon", false),
                        new Question("What is the highest-grossing video game of all time?", encodedPassword, "Single Choice", "A", "Minecraft", "GTA V", "Tetris", "Fortnite", false),
                        new Question("In which game do players build and design theme parks?", encodedPassword, "Single Choice", "B", "Zoo Tycoon", "RollerCoaster Tycoon", "SimCity", "Theme Hospital", false),
                        new Question("What is the name of the lead character in \"Red Dead Redemption\"?", encodedPassword, "Single Choice", "B", "Arthur Morgan", "John Marston", "Niko Bellic", "Trevor Philips", false),
                        new Question("Which game is set in the post-apocalyptic Boston area?", encodedPassword, "Single Choice", "B", "The Division", "Fallout 4", "Far Cry 5", "Cyberpunk 2077", false),
                        new Question("In \"Among Us\", what role can a player have besides a crewmate?", encodedPassword, "Single Choice", "B", "Healer", "Imposter", "Saboteur", "Spy", false),
                        new Question("Which game series features treasure hunter Nathan Drake?", encodedPassword, "Single Choice", "A", "Uncharted", "Tomb Raider", "Far Cry", "Hitman", false),
                        new Question("Which racing game series is known for its open-world gameplay?", encodedPassword, "Single Choice", "B", "Need for Speed", "Forza Horizon", "Gran Turismo", "Dirt Rally", false),
                        new Question("What is the name of the protagonist in \"The Legend of Zelda\"?", encodedPassword, "Single Choice", "C", "Zelda", "Ganon", "Link", "Sheik", false),
                        new Question("Which game takes place on the island of Tsushima?", encodedPassword, "Single Choice", "A", "Ghost of Tsushima", "Sekiro", "Nioh", "Dark Souls", false),
                        new Question("Which company developed the \"Super Mario\" series?", encodedPassword, "Single Choice", "B", "Sega", "Nintendo", "Sony", "Microsoft", false),
                        new Question("In which game do you fight in a post-apocalyptic world ruled by machines?", encodedPassword, "Single Choice", "A", "Horizon Zero Dawn", "The Last of Us", "Mad Max", "Metro Exodus", false),
                        new Question("Which game features the character Kratos, the God of War?", encodedPassword, "Single Choice", "B", "The Elder Scrolls", "God of War", "Dark Souls", "Diablo", false),
                        new Question("In \"Call of Duty: Warzone\", what is the in-game currency called?", encodedPassword, "Single Choice", "D", "Dollars", "Rupees", "Credits", "Cash", false),
                        new Question("Which game introduced the battle royale genre to the mainstream?", encodedPassword, "Single Choice", "B", "Minecraft", "PUBG", "Apex Legends", "Fortnite", false),
                        new Question("What is the name of the protagonist in \"Cyberpunk 2077\"?", encodedPassword, "Single Choice", "A", "V", "Johnny Silverhand", "Jill Valentine", "Corvo Attano", false),
                        new Question("Which game series revolves around a criminal underworld in a fictional U.S. city?", encodedPassword, "Single Choice", "B", "Assassin's Creed", "Grand Theft Auto", "Saints Row", "Mafia", false),
                        new Question("In which game do players control a pink puffball character named Kirby?", encodedPassword, "Single Choice", "A", "Kirby Star Allies", "Super Mario Odyssey", "Donkey Kong Country", "Zelda", false),
                        new Question("What is the name of the first-person shooter game developed by Blizzard Entertainment?", encodedPassword, "Single Choice", "A", "Overwatch", "Fortnite", "Counter-Strike", "Call of Duty", false),
                        new Question("In which game does the phrase \"The cake is a lie\" come from?", encodedPassword, "Single Choice", "A", "Portal", "Half-Life", "Doom", "Minecraft", false),
                        new Question("What is the name of the artificial intelligence that assists Master Chief in \"Halo\"?", encodedPassword, "Single Choice", "A", "Cortana", "Alexa", "Siri", "Ada", false),
                        new Question("Which game series features the Vault Boy mascot?", encodedPassword, "Single Choice", "B", "BioShock", "Fallout", "Far Cry", "Metro", false),
                        new Question("Which survival game involves crafting, building, and exploring a procedurally generated world?", encodedPassword, "Single Choice", "B", "No Man's Sky", "Minecraft", "The Forest", "Rust", false),
                        new Question("What is the main objective in \"The Sims\" games?", encodedPassword, "Single Choice", "C", "Win races", "Survive a war", "Build and manage lives", "Fight monsters", false),
                        new Question("Which character is known as the \"Blue Blur\"?", encodedPassword, "Single Choice", "B", "Mega Man", "Sonic the Hedgehog", "Crash Bandicoot", "Spyro", false),
                        new Question("In which game do players explore a galaxy with planets, spaceships, and alien races?", encodedPassword, "Single Choice", "A", "Mass Effect", "Starcraft", "Destiny", "Elite Dangerous", false),
                        new Question("What is the name of the princess in the \"Super Mario\" series?", encodedPassword, "Single Choice", "D", "Princess Zelda", "Princess Daisy", "Princess Rosalina", "Princess Peach", false),
                        new Question("Which game allows players to explore ancient Greece as a mercenary?", encodedPassword, "Single Choice", "A", "Assassin's Creed Odyssey", "Skyrim", "The Witcher 3", "God of War", false),

                        new Question("In which game can you build cities and manage resources?", encodedPassword, "Single Choice", "B", "Stardew Valley", "Cities: Skylines", "Terraria", "The Sims", false),
                        new Question("Which fighting game franchise features characters like Ryu and Ken?", encodedPassword, "Single Choice", "B", "Tekken", "Street Fighter", "Mortal Kombat", "Super Smash Bros.", false)
                ));

                //Anime Questions
                questions.addAll(Arrays.asList(
                        new Question("Which anime features a boy who wants to become the Pirate King?", encodedPassword, "Single Choice", "B", "Naruto", "One Piece", "Dragon Ball Z", "Bleach", false),
                        new Question("What is the name of Goku's home planet in Dragon Ball Z?", encodedPassword, "Single Choice", "A", "Vegeta", "Namek", "Earth", "Planet 7", false),
                        new Question("Who is the main character in \"Naruto\"?", encodedPassword, "Single Choice", "A", "Naruto Uzumaki", "Sasuke Uchiha", "Sakura Haruno", "Kakashi Hatake", false),
                        new Question("In \"Attack on Titan\", what are the giant creatures called?", encodedPassword, "Single Choice", "B", "Angels", "Titans", "Dragons", "Monsters", false),
                        new Question("Which anime features a notebook that can kill anyone whose name is written in it?", encodedPassword, "Single Choice", "A", "Death Note", "Tokyo Ghoul", "Attack on Titan", "One Punch Man", false),
                        new Question("Who created the anime \"Spirited Away\"?", encodedPassword, "Single Choice", "A", "Hayao Miyazaki", "Makoto Shinkai", "Satoshi Kon", "Osamu Tezuka", false),
                        new Question("What is the primary goal of Luffy in \"One Piece\"?", encodedPassword, "Single Choice", "C", "To find the Philosopher's Stone", "To become Hokage", "To become the Pirate King", "To destroy the Titans", false),
                        new Question("In \"My Hero Academia\", what are the characters' powers called?", encodedPassword, "Single Choice", "B", "Ninjutsu", "Quirks", "Alchemy", "Bankai", false),
                        new Question("Which anime is about alchemy and the Philosopher's Stone?", encodedPassword, "Single Choice", "B", "One Piece", "Fullmetal Alchemist", "Naruto", "Attack on Titan", false),
                        new Question("Who is the main character in \"Sword Art Online\"?", encodedPassword, "Single Choice", "A", "Kirito", "Eren", "Luffy", "Saitama", false),
                        new Question("What sport is featured in the anime \"Haikyuu!!\"?", encodedPassword, "Single Choice", "D", "Basketball", "Tennis", "Soccer", "Volleyball", false),
                        new Question("In \"Demon Slayer\", who is Tanjiro's sister?", encodedPassword, "Single Choice", "A", "Nezuko", "Hinata", "Mikasa", "Sakura", false),
                        new Question("What is the main theme of \"Your Name\"?", encodedPassword, "Single Choice", "C", "Cooking", "Romance", "Body-swapping", "Sports", false),
                        new Question("Which anime character is known for the Rasengan?", encodedPassword, "Single Choice", "C", "Luffy", "Goku", "Naruto", "Ichigo", false),
                        new Question("Who is the creator of the \"Dragon Ball\" series?", encodedPassword, "Single Choice", "B", "Eiichiro Oda", "Akira Toriyama", "Hajime Isayama", "Yoshihiro Togashi", false)
                ));

                // History Questions
                questions.addAll(Arrays.asList(
                        new Question("Who was the first President of the United States?", encodedPassword, "Single Choice", "B", "Abraham Lincoln", "George Washington", "John Adams", "Thomas Jefferson", false),
                        new Question("Which year did World War I begin?", encodedPassword, "Single Choice", "B", "1912", "1914", "1918", "1920", false),
                        new Question("Who was the last queen of France before the French Revolution?", encodedPassword, "Single Choice", "A", "Marie Antoinette", "Catherine the Great", "Queen Victoria", "Eleanor of Aquitaine", false),
                        new Question("What empire did Julius Caesar belong to?", encodedPassword, "Single Choice", "B", "Greek", "Roman", "Ottoman", "Mongol", false),
                        new Question("In which year did the Titanic sink?", encodedPassword, "Single Choice", "C", "1910", "1911", "1912", "1913", false),
                        new Question("Who was the first man to walk on the moon?", encodedPassword, "Single Choice", "B", "Yuri Gagarin", "Neil Armstrong", "Buzz Aldrin", "Michael Collins", false),
                        new Question("Which ancient civilization built the pyramids?", encodedPassword, "Single Choice", "C", "Romans", "Greeks", "Egyptians", "Incas", false),
                        new Question("What was the name of the ship that brought the Pilgrims to America?", encodedPassword, "Single Choice", "B", "Santa Maria", "Mayflower", "Endeavour", "Titanic", false),
                        new Question("Who was the British Prime Minister during World War II?", encodedPassword, "Single Choice", "A", "Winston Churchill", "Margaret Thatcher", "Tony Blair", "Neville Chamberlain", false),
                        new Question("Which city was divided by a wall during the Cold War?", encodedPassword, "Single Choice", "B", "Paris", "Berlin", "Moscow", "Warsaw", false),
                        new Question("Who was assassinated in 1963 in Dallas?", encodedPassword, "Single Choice", "B", "Martin Luther King Jr.", "John F. Kennedy", "Abraham Lincoln", "Robert Kennedy", false),
                        new Question("What was the main cause of the American Civil War?", encodedPassword, "Single Choice", "B", "Taxation", "Slavery", "Religion", "Territory disputes", false),
                        new Question("Who was the first emperor of Rome?", encodedPassword, "Single Choice", "A", "Augustus", "Nero", "Julius Caesar", "Caligula", false),
                        new Question("Which event started World War I?", encodedPassword, "Single Choice", "B", "Sinking of the Lusitania", "Assassination of Archduke Franz Ferdinand", "Treaty of Versailles", "Invasion of Poland", false),
                        new Question("When was the United Nations established?", encodedPassword, "Single Choice", "B", "1919", "1945", "1950", "1965", false)
                ));

                // Music Questions
                questions.addAll(Arrays.asList(
                        new Question("Who is known as the 'King of Pop'?", encodedPassword, "Single Choice", "B", "Elvis Presley", "Michael Jackson", "Prince", "Freddie Mercury", false),
                        new Question("Which band released the album 'Abbey Road'?", encodedPassword, "Single Choice", "B", "The Rolling Stones", "The Beatles", "Pink Floyd", "Led Zeppelin", false),
                        new Question("Who is the lead singer of the band Queen?", encodedPassword, "Single Choice", "B", "Elton John", "Freddie Mercury", "David Bowie", "Mick Jagger", false),
                        new Question("Which instrument does a pianist play?", encodedPassword, "Single Choice", "B", "Guitar", "Piano", "Violin", "Drums", false),
                        new Question("What is the highest female singing voice?", encodedPassword, "Single Choice", "B", "Alto", "Soprano", "Tenor", "Bass", false),
                        new Question("Which composer became deaf later in life?", encodedPassword, "Single Choice", "B", "Mozart", "Beethoven", "Bach", "Tchaikovsky", false),
                        new Question("Which country is famous for the origin of reggae music?", encodedPassword, "Single Choice", "B", "USA", "Jamaica", "Brazil", "Cuba", false),
                        new Question("What is the name of the musical symbol that represents silence?", encodedPassword, "Single Choice", "B", "Note", "Rest", "Beat", "Chord", false),
                        new Question("Which famous musician was part of the group The Jackson 5?", encodedPassword, "Single Choice", "B", "Prince", "Michael Jackson", "Stevie Wonder", "James Brown", false),
                        new Question("Who is the composer of 'The Four Seasons'?", encodedPassword, "Single Choice", "C", "Bach", "Mozart", "Vivaldi", "Chopin", false),
                        new Question("Which classical composer wrote 'Fur Elise'?", encodedPassword, "Single Choice", "B", "Mozart", "Beethoven", "Bach", "Schubert", false),
                        new Question("What is the name of the music genre combining rap and rock?", encodedPassword, "Single Choice", "C", "Hip-hop", "Jazz Fusion", "Nu Metal", "Grunge", false),
                        new Question("Who is the 'Queen of Pop'?", encodedPassword, "Single Choice", "A", "Madonna", "Whitney Houston", "Lady Gaga", "Adele", false),
                        new Question("Which pop star sang the hit song 'Bad Romance'?", encodedPassword, "Single Choice", "B", "Britney Spears", "Lady Gaga", "Katy Perry", "Rihanna", false),
                        new Question("What is the standard number of strings on a guitar?", encodedPassword, "Single Choice", "C", "4", "5", "6", "7", false),
                        new Question("Which musical genre originated in the Bronx, New York, during the 1970s?", encodedPassword, "Single Choice", "B", "Jazz", "Hip-hop", "Rock", "Reggae", false),
                        new Question("Who is the best-selling female artist of all time?", encodedPassword, "Single Choice", "D", "Beyoncé", "Whitney Houston", "Rihanna", "Madonna", false),
                        new Question("Which instrument has the most keys?", encodedPassword, "Single Choice", "A", "Piano", "Saxophone", "Harp", "Clarinet", false),
                        new Question("What is the name of the instrument that Yo-Yo Ma is famous for playing?", encodedPassword, "Single Choice", "B", "Violin", "Cello", "Piano", "Double Bass", false),
                        new Question("Who is known for the hit 'Purple Rain'?", encodedPassword, "Single Choice", "C", "David Bowie", "Michael Jackson", "Prince", "Elton John", false),
                        new Question("What was Elvis Presley’s nickname?", encodedPassword, "Single Choice", "C", "The Boss", "The King of Pop", "The King of Rock and Roll", "The Godfather of Soul", false),
                        new Question("Who composed the opera 'The Magic Flute'?", encodedPassword, "Single Choice", "C", "Tchaikovsky", "Beethoven", "Mozart", "Verdi", false),
                        new Question("What is the tempo marking for a slow pace in music?", encodedPassword, "Single Choice", "B", "Presto", "Adagio", "Allegro", "Vivace", false),
                        new Question("Which genre of music is Billie Eilish known for?", encodedPassword, "Single Choice", "B", "Country", "Pop", "Rock", "Jazz", false),
                        new Question("Who was the lead guitarist of the band Nirvana?", encodedPassword, "Single Choice", "B", "Jimi Hendrix", "Kurt Cobain", "Jimmy Page", "Eddie Van Halen", false),
                        new Question("Which artist is known as the 'Material Girl'?", encodedPassword, "Single Choice", "A", "Madonna", "Katy Perry", "Lady Gaga", "Britney Spears", false),
                        new Question("Which band is known for the song 'Bohemian Rhapsody'?", encodedPassword, "Single Choice", "C", "The Rolling Stones", "The Beatles", "Queen", "Led Zeppelin", false),
                        new Question("What is the name of the Grammy Award for Best Album of the Year?", encodedPassword, "Single Choice", "B", "Golden Note", "Gramophone", "Golden Album", "Grammy", false),
                        new Question("Which instrument belongs to the woodwind family?", encodedPassword, "Single Choice", "B", "Trumpet", "Clarinet", "Violin", "Trombone", false),
                        new Question("Who is known for the song 'Rolling in the Deep'?", encodedPassword, "Single Choice", "B", "Beyoncé", "Adele", "Rihanna", "Taylor Swift", false)
                ));

                // Politics Questions
                questions.addAll(Arrays.asList(
                        new Question("What is the political ideology focused on social equality?", encodedPassword, "Single Choice", "B", "Capitalism", "Communism", "Fascism", "Liberalism", false),
                        new Question("Who was the first female Prime Minister of the UK?", encodedPassword, "Single Choice", "B", "Theresa May", "Margaret Thatcher", "Angela Merkel", "Indira Gandhi", false),
                        new Question("What is the highest law of the United States?", encodedPassword, "Single Choice", "B", "The Bill of Rights", "The Constitution", "The Declaration of Independence", "The Magna Carta", false),
                        new Question("Which political system involves rule by the people?", encodedPassword, "Single Choice", "C", "Monarchy", "Dictatorship", "Democracy", "Oligarchy", false),
                        new Question("What is the lower house of the United States Congress called?", encodedPassword, "Single Choice", "B", "Senate", "House of Representatives", "Parliament", "Cabinet", false),
                        new Question("Who is the head of state in a constitutional monarchy?", encodedPassword, "Single Choice", "C", "President", "Prime Minister", "Monarch", "Chancellor", false),
                        new Question("Which political system did Nazi Germany follow?", encodedPassword, "Single Choice", "A", "Fascism", "Democracy", "Socialism", "Monarchy", false),
                        new Question("What is the political term for a sudden overthrow of the government?", encodedPassword, "Single Choice", "B", "Revolution", "Coup d'etat", "Referendum", "Election", false),
                        new Question("Which international organization was founded in 1945 to promote peace?", encodedPassword, "Single Choice", "B", "NATO", "UN", "EU", "World Bank", false),
                        new Question("Who is known for the political theory of 'The Social Contract'?", encodedPassword, "Single Choice", "C", "Karl Marx", "John Locke", "Jean-Jacques Rousseau", "Thomas Hobbes", false),
                        new Question("Which country has a one-party system?", encodedPassword, "Single Choice", "C", "USA", "Germany", "China", "France", false),
                        new Question("What does NATO stand for?", encodedPassword, "Single Choice", "A", "North Atlantic Treaty Organization", "National Aeronautics Treaty Organization", "North African Trade Organization", "None of the above", false),
                        new Question("Which U.S. president resigned due to the Watergate scandal?", encodedPassword, "Single Choice", "A", "Richard Nixon", "Gerald Ford", "John F. Kennedy", "Jimmy Carter", false),
                        new Question("Who is the current leader of the United Nations?", encodedPassword, "Single Choice", "A", "Antonio Guterres", "Ban Ki-moon", "Kofi Annan", "Angela Merkel", false),
                        new Question("What is the primary legislative body of the European Union?", encodedPassword, "Single Choice", "B", "European Commission", "European Parliament", "Council of the EU", "European Court of Justice", false)
                ));

                // General Questions
                questions.addAll(Arrays.asList(
                        new Question("How many planets are in our solar system?", encodedPassword, "Single Choice", "B", "7", "8", "9", "10", false),
                        new Question("Which element has the chemical symbol O?", encodedPassword, "Single Choice", "C", "Gold", "Silver", "Oxygen", "Carbon", false),
                        new Question("Who wrote 'Hamlet'?", encodedPassword, "Single Choice", "A", "Shakespeare", "Goethe", "Schiller", "Dostoevsky", false),
                        new Question("How many continents are there?", encodedPassword, "Single Choice", "C", "5", "6", "7", "8", false),
                        new Question("What is the capital of Japan?", encodedPassword, "Single Choice", "C", "Beijing", "Seoul", "Tokyo", "Bangkok", false),
                        new Question("What color is chlorophyll?", encodedPassword, "Single Choice", "C", "Red", "Blue", "Green", "Yellow", false),
                        new Question("How many legs does a spider have?", encodedPassword, "Single Choice", "B", "6", "8", "10", "12", false),
                        new Question("What is the square root of 81?", encodedPassword, "Single Choice", "C", "7", "8", "9", "10", false),
                        new Question("In which year did World War II begin?", encodedPassword, "Single Choice", "B", "1938", "1939", "1940", "1941", false),
                        new Question("Which instrument has keys?", encodedPassword, "Single Choice", "C", "Guitar", "Flute", "Piano", "Trumpet", false),
                        new Question("What is the largest mammal?", encodedPassword, "Single Choice", "B", "Elephant", "Blue Whale", "Giraffe", "Rhino", false),
                        new Question("Which planet is known as the Red Planet?", encodedPassword, "Single Choice", "C", "Earth", "Venus", "Mars", "Jupiter", false),
                        new Question("What is the longest river in the world?", encodedPassword, "Single Choice", "B", "Amazon", "Nile", "Yangtze", "Mississippi", false),
                        new Question("Who painted the Mona Lisa?", encodedPassword, "Single Choice", "C", "Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet", false),
                        new Question("Which gas do plants absorb from the atmosphere?", encodedPassword, "Single Choice", "B", "Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen", false),
                        new Question("How many teeth does an adult human have?", encodedPassword, "Single Choice", "C", "28", "30", "32", "34", false),
                        new Question("What is the smallest unit of life?", encodedPassword, "Single Choice", "A", "Cell", "Atom", "Molecule", "Organ", false),
                        new Question("What is the largest organ in the human body?", encodedPassword, "Single Choice", "D", "Heart", "Liver", "Lungs", "Skin", false),
                        new Question("What is the capital of Australia?", encodedPassword, "Single Choice", "C", "Sydney", "Melbourne", "Canberra", "Perth", false),
                        new Question("Which metal is liquid at room temperature?", encodedPassword, "Single Choice", "B", "Iron", "Mercury", "Lead", "Silver", false),
                        new Question("What is the hardest natural substance on Earth?", encodedPassword, "Single Choice", "B", "Gold", "Diamond", "Iron", "Steel", false),
                        new Question("What is the chemical formula for water?", encodedPassword, "Single Choice", "C", "CO2", "O2", "H2O", "CH4", false),
                        new Question("Which planet is closest to the Sun?", encodedPassword, "Single Choice", "B", "Venus", "Mercury", "Earth", "Mars", false),
                        new Question("How many bones are in the human body?", encodedPassword, "Single Choice", "B", "198", "206", "214", "220", false),
                        new Question("What is the name of the largest ocean?", encodedPassword, "Single Choice", "C", "Atlantic", "Indian", "Pacific", "Southern", false),
                        new Question("Which country has the most people?", encodedPassword, "Single Choice", "D", "India", "USA", "Russia", "China", false),
                        new Question("What is the most spoken language in the world?", encodedPassword, "Single Choice", "C", "Spanish", "English", "Mandarin", "Hindi", false),
                        new Question("Which city is known as the Big Apple?", encodedPassword, "Single Choice", "C", "Los Angeles", "San Francisco", "New York", "Chicago", false),
                        new Question("What is the name of the smallest planet in the solar system?", encodedPassword, "Single Choice", "B", "Mars", "Mercury", "Venus", "Pluto", false),
                        new Question("Who developed the theory of relativity?", encodedPassword, "Single Choice", "C", "Isaac Newton", "Galileo Galilei", "Albert Einstein", "Marie Curie", false)
                ));

                // School Knowledge Questions
                questions.addAll(Arrays.asList(
                        new Question("What is the capital of France?", encodedPassword, "Single Choice", "B", "Berlin", "Paris", "Rome", "Madrid", false),
                        new Question("What is the chemical symbol for water?", encodedPassword, "Single Choice", "B", "O2", "H2O", "CO2", "HO", false),
                        new Question("Who discovered gravity?", encodedPassword, "Single Choice", "B", "Galileo", "Newton", "Einstein", "Darwin", false),
                        new Question("What is 5 multiplied by 6?", encodedPassword, "Single Choice", "B", "25", "30", "35", "40", false),
                        new Question("Which planet is closest to the Sun?", encodedPassword, "Single Choice", "D", "Earth", "Venus", "Mars", "Mercury", false),
                        new Question("What is the square root of 64?", encodedPassword, "Single Choice", "C", "6", "7", "8", "9", false),
                        new Question("Who wrote the play 'Romeo and Juliet'?", encodedPassword, "Single Choice", "A", "Shakespeare", "Hemingway", "Dickens", "Tolstoy", false),
                        new Question("What is the powerhouse of the cell?", encodedPassword, "Single Choice", "B", "Nucleus", "Mitochondria", "Chloroplast", "Ribosome", false),
                        new Question("In which country did the Olympic Games originate?", encodedPassword, "Single Choice", "B", "Egypt", "Greece", "Rome", "China", false),
                        new Question("What is the process by which plants make food?", encodedPassword, "Single Choice", "B", "Respiration", "Photosynthesis", "Evaporation", "Condensation", false),
                        new Question("What is the boiling point of water in degrees Celsius?", encodedPassword, "Single Choice", "B", "90", "100", "110", "120", false),
                        new Question("What is the largest organ in the human body?", encodedPassword, "Single Choice", "D", "Heart", "Lungs", "Liver", "Skin", false),
                        new Question("Which language is the most spoken worldwide?", encodedPassword, "Single Choice", "C", "English", "Spanish", "Mandarin", "Hindi", false),
                        new Question("How many continents are there?", encodedPassword, "Single Choice", "C", "5", "6", "7", "8", false),
                        new Question("What is the first element on the periodic table?", encodedPassword, "Single Choice", "B", "Helium", "Hydrogen", "Oxygen", "Carbon", false)
                ));

                // Netflix Questions
                questions.addAll(Arrays.asList(
                        new Question("What is the name of the supernatural series featuring Eleven?", encodedPassword, "Single Choice", "B", "The Witcher", "Stranger Things", "Dark", "Lucifer", false),
                        new Question("Which series is about a heist on the Royal Mint of Spain?", encodedPassword, "Single Choice", "C", "Breaking Bad", "Narcos", "Money Heist", "Ozark", false),
                        new Question("In 'The Witcher', what is Geralt's profession?", encodedPassword, "Single Choice", "C", "Knight", "Mage", "Witcher", "Soldier", false),
                        new Question("Which Netflix series is based on a chess prodigy?", encodedPassword, "Single Choice", "C", "The Crown", "Mindhunter", "The Queen's Gambit", "Lupin", false),
                        new Question("Who plays the role of Pablo Escobar in 'Narcos'?", encodedPassword, "Single Choice", "B", "Pedro Pascal", "Wagner Moura", "Gael García Bernal", "Diego Luna", false),
                        new Question("In 'Lucifer', what is Lucifer Morningstar's job in Los Angeles?", encodedPassword, "Single Choice", "C", "Detective", "Lawyer", "Club Owner", "Doctor", false),
                        new Question("Which Netflix show is set in the fictional town of Hawkins?", encodedPassword, "Single Choice", "A", "Stranger Things", "Riverdale", "Dark", "Locke & Key", false),
                        new Question("What is the name of the documentary series about a convicted big cat owner?", encodedPassword, "Single Choice", "A", "Tiger King", "Making a Murderer", "Wild Wild Country", "Don't F**k with Cats", false),
                        new Question("Who is the main character in 'BoJack Horseman'?", encodedPassword, "Single Choice", "A", "A horse", "A cat", "A dog", "A bird", false),
                        new Question("In which show does Beth Harmon become a chess champion?", encodedPassword, "Single Choice", "B", "Mindhunter", "The Queen's Gambit", "Lupin", "Narcos", false),
                        new Question("Which show revolves around the British royal family?", encodedPassword, "Single Choice", "A", "The Crown", "Bridgerton", "House of Cards", "Reign", false),
                        new Question("Who is the protagonist of 'You'?", encodedPassword, "Single Choice", "A", "Joe Goldberg", "Walter White", "Dexter Morgan", "Hannibal Lecter", false),
                        new Question("Which Netflix series is a German time-travel thriller?", encodedPassword, "Single Choice", "B", "Stranger Things", "Dark", "The OA", "Sense8", false),
                        new Question("What game is central to the plot of 'Squid Game'?", encodedPassword, "Single Choice", "C", "Poker", "Chess", "Children's Games", "Survival Games", false),
                        new Question("In 'The Umbrella Academy', what superpower does Number Five have?", encodedPassword, "Single Choice", "D", "Super strength", "Teleportation", "Telepathy", "Time travel", false),
                        new Question("What is the main plot of 'Ozark'?", encodedPassword, "Single Choice", "B", "Cooking", "Drug money laundering", "Bank heists", "Political intrigue", false),
                        new Question("Who is the lead actor in 'The Witcher'?", encodedPassword, "Single Choice", "A", "Henry Cavill", "Chris Hemsworth", "Keanu Reeves", "Tom Hardy", false),
                        new Question("Which show features a group of kids fighting supernatural creatures?", encodedPassword, "Single Choice", "A", "Stranger Things", "The Witcher", "Locke & Key", "The Umbrella Academy", false),
                        new Question("What is the setting of 'Bridgerton'?", encodedPassword, "Single Choice", "B", "Medieval England", "Regency-era London", "1920s Paris", "Modern-day New York", false),
                        new Question("Which series features a hacker group called fsociety?", encodedPassword, "Single Choice", "A", "Mr. Robot", "Money Heist", "The OA", "Sense8", false),
                        new Question("In 'House of Cards', what is Frank Underwood’s ultimate goal?", encodedPassword, "Single Choice", "B", "Become a senator", "Become President", "Overthrow the government", "Start a business", false),
                        new Question("Who plays Queen Elizabeth II in the first two seasons of 'The Crown'?", encodedPassword, "Single Choice", "B", "Olivia Colman", "Claire Foy", "Helena Bonham Carter", "Emma Corrin", false),
                        new Question("Which show is set in the fictional town of Riverdale?", encodedPassword, "Single Choice", "A", "Riverdale", "The Society", "Dark", "Locke & Key", false),
                        new Question("What is the main character's profession in 'Narcos'?", encodedPassword, "Single Choice", "C", "Banker", "Politician", "Drug lord", "Police officer", false),
                        new Question("Which Netflix show features a French thief inspired by Arsène Lupin?", encodedPassword, "Single Choice", "C", "The Umbrella Academy", "Money Heist", "Lupin", "The Crown", false),
                        new Question("In 'Stranger Things', who plays the role of Chief Jim Hopper?", encodedPassword, "Single Choice", "A", "David Harbour", "Winona Ryder", "Charlie Heaton", "Finn Wolfhard", false),
                        new Question("Which character in 'The Witcher' is a powerful sorceress?", encodedPassword, "Single Choice", "B", "Ciri", "Yennefer", "Fringilla", "Tissaia", false),
                        new Question("What is the main plot of 'The Haunting of Hill House'?", encodedPassword, "Single Choice", "C", "Time travel", "Murder mystery", "A haunted mansion", "Zombie apocalypse", false),
                        new Question("Who is the lead character in 'The Queen's Gambit'?", encodedPassword, "Single Choice", "B", "Jane Harmon", "Beth Harmon", "Sarah Harmon", "Amy Harmon", false),
                        new Question("Which Netflix series focuses on the Colombian drug trade?", encodedPassword, "Single Choice", "B", "Money Heist", "Narcos", "Breaking Bad", "Ozark", false),
                        new Question("Which supernatural show features a family with dark secrets and keys to hidden powers?", encodedPassword, "Single Choice", "B", "The Umbrella Academy", "Locke & Key", "Stranger Things", "The Witcher", false),
                        new Question("What is the name of the professor in 'Money Heist'?", encodedPassword, "Single Choice", "C", "Raquel", "Tokyo", "Sergio", "Berlin", false),
                        new Question("Who plays the role of Geralt in 'The Witcher'?", encodedPassword, "Single Choice", "B", "Chris Evans", "Henry Cavill", "Jason Momoa", "Tom Holland", false),
                        new Question("Which Netflix show is a period drama based on Julia Quinn’s novels?", encodedPassword, "Single Choice", "A", "Bridgerton", "The Crown", "The Witcher", "Reign", false),
                        new Question("What is the main occupation of Joe Goldberg in 'You'?", encodedPassword, "Single Choice", "B", "Writer", "Bookstore manager", "Detective", "Chef", false),
                        new Question("Which show is about a group of superheroes adopted by a billionaire?", encodedPassword, "Single Choice", "B", "The Boys", "The Umbrella Academy", "Doom Patrol", "Locke & Key", false),
                        new Question("In 'Squid Game', what color tracksuits do the players wear?", encodedPassword, "Single Choice", "C", "Blue", "Red", "Green", "Yellow", false),
                        new Question("Who is the main antagonist in the first season of 'Stranger Things'?", encodedPassword, "Single Choice", "B", "Mind Flayer", "Demogorgon", "Vecna", "Billy", false),
                        new Question("What is the name of the serial killer in 'Mindhunter' who helps the FBI?", encodedPassword, "Single Choice", "A", "Ed Kemper", "Ted Bundy", "Charles Manson", "Dennis Rader", false)
                ));


                // questions to quiz
                List<Question> scienceQuestions = questions.subList(0, 5);
                List<Question> historyQuestions = questions.subList(5, 10);
                List<Question> sportsQuestions = questions.subList(10, 15);
                List<Question> techQuestions = questions.subList(15, 20);

                List<Question> gamesQuestions = questions.subList(20, 60);
                List<Question> animeQuestions = questions.subList(60, 75);
                List<Question> historyQuestions2 = questions.subList(75, 90);
                List<Question> musicQuestions = questions.subList(90, 120);
                List<Question> politicsQuestions = questions.subList(120, 135);
                List<Question> generalQuestions = questions.subList(135, 165);
                List<Question> schoolQuestions = questions.subList(165, 180);
                List<Question> netflixQuestions = questions.subList(180, 219);

                scienceQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(0))));
                historyQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(1))));
                sportsQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(2))));
                techQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(3))));

                gamesQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(5))));
                animeQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(6))));
                historyQuestions2.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(7))));
                musicQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(8))));
                politicsQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(9))));
                generalQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(10))));
                schoolQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(11))));
                netflixQuestions.forEach(question -> question.setQuizzes(Collections.singletonList(quizzes.get(12))));

                // tags to questions
                scienceQuestions.forEach(question -> question.setTags(Collections.singletonList(tags.get(0)))); // Science
                scienceQuestions.get(0).setTags(Arrays.asList(tags.get(0), tags.get(3))); // Science + Math
                scienceQuestions.get(1).setTags(Arrays.asList(tags.get(0), tags.get(12))); // Science + Space
                scienceQuestions.get(2).setTags(Arrays.asList(tags.get(0), tags.get(12))); // Science + Space

                historyQuestions.forEach(question -> question.setTags(Collections.singletonList(tags.get(1)))); // History

                sportsQuestions.forEach(question -> question.setTags(Collections.singletonList(tags.get(2)))); // Sports
                sportsQuestions.get(0).setTags(Arrays.asList(tags.get(2), tags.get(1))); // Sports + History
                sportsQuestions.get(2).setTags(Arrays.asList(tags.get(2), tags.get(1))); // Sports + History
                sportsQuestions.get(3).setTags(Arrays.asList(tags.get(2), tags.get(1))); // Sports + History

                techQuestions.forEach(question -> question.setTags(Collections.singletonList(tags.get(4)))); // Technology
                techQuestions.get(1).setTags(Arrays.asList(tags.get(4), tags.get(11))); // Tech + Programming
                techQuestions.get(2).setTags(Arrays.asList(tags.get(4), tags.get(11), tags.get(1))); // Tech + Programming + History
                techQuestions.get(3).setTags(Arrays.asList(tags.get(4), tags.get(11))); // Tech + Programming
                techQuestions.get(3).setTags(Collections.singletonList(tags.get(11))); // Programming

                // Shuffle questions to randomize order
                //Collections.shuffle(questions);

                // Save questions
                questions.forEach(questionService::addQuestion);
            }

        };
    }
}

