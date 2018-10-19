package reviews.reviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner {
	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private AuthorRepository authorRepo;

	@Resource
	private GenreRepository genreRepo;

	@Override
	public void run(String... args) throws Exception {

		Genre sf = genreRepo.save(new Genre("science fiction"));
		Genre fantasy = genreRepo.save(new Genre("fantasy"));
		Genre steampunk = genreRepo.save(new Genre("steampunk"));
		Genre memoir = genreRepo.save(new Genre("memoir"));
		Genre nonfiction = genreRepo.save(new Genre("non-fiction"));
		Genre mystery = genreRepo.save(new Genre("mystery"));
		Genre humor = genreRepo.save(new Genre("humor"));
		Genre fiction = genreRepo.save(new Genre("fiction"));

		Review orphanKeeper = reviewRepo.save(new Review("The Orphan Keeper",
				"Nice story and all, but the writing is terrible. An editor really needed to fix this. <br/>"
						+ "AS A SINGLE EXAMPLE from page 199, filled with things put in to be a fancy writer:<br/><blockquote>"
						+ "Fred was reading on the couch when Linda burst in. \"I have news,\" she announced, but in lieu of eager eyes, she was packing worry.<br/>"
						+ "Fred lowered his newspaper.\r\n" + "\"I quit my job,\" she said.<br/>"
						+ "Her words drained shamefully onto the carpet. She looked as if she were admitting guilt to capital murder.<br/>"
						+ "Fred arched forward, cupped his ears. She loved her job. He must have misunderstood.<br/>"
						+ "\"Say what?\"<br/>"
						+ "If he sounded gruff, he didn't mean it. His tone, like his face, often wore its angry coat. It was more comfortable.</blockquote>",
				"/images/orphanKeeper.jpg", fiction));
		Review womanWindow = reviewRepo.save(new Review("The Woman in the Window",
				"It's good that the narrator/main character is so into Hitchcock movies and noirs that it becomes a plot point, because the story is like Vertigo and Rear Window and Gaslight in particular. It's a not terribly deep but pretty entertaining thriller.\r\n"
						+ "One negative comment: the main character is a woman, but has a habit of describing other women's breasts. I've already returned my copy to the library, but one of the descriptions was something like \"her breasts were perkily embraced by her bra.\" I had to look up whether \"A.J. Finn\" was a man or a woman. To my utter lack of surprise, it's a man!",
				"/images/womanInTheWindow.jpg", fiction, mystery));
		Review monkeyGod = reviewRepo.save(new Review("The Lost City of the Monkey God: A True Story",
				"The engrossing story of the quest for a fabled lost city deep in the jungle of Honduras. I didn't read any blurbs or summaries before reading the book, and I enjoyed having each discovery and different aspect of the story unfold.",
				"/images/lostCityOfTheMonkeyGod.jpg", nonfiction));
		Review letsPretend = reviewRepo.save(new Review("Let's Pretend This Never Happened: A Mostly True Memoir",
				"A hilarious and poignant memoir about family, mental illness, and taxidermy.",
				"/images/letsPretendThisNeverHappened.jpg", memoir, humor, nonfiction));
		Review janeTexts = reviewRepo.save(new Review(
				"Texts From Jane Eyre: And Other Conversations with Your Favorite Literary Characters",
				"Hilarious book for the very literate! If you've read a lot of classic lit then this may be the book for you! What if Edgar Allan Poe, Emily Dickinson, or Rene Descartes had been able to text friends? What if the characters from Hamlet, Wuthering Heights, Little Women, The Lorax, or \"The Yellow Wallpaper\" had SMS?",
				"/images/textsFromJaneEyre.jpg", humor));
		Review artemis = reviewRepo.save(new Review("Artemis",
				"A fun-ish romp, but I didn't buy the protagonist as a woman. Take away the male characters' lust for her, the sex jokes and there would be nothing to make her female. She felt like a teenage boy. Also, there were very few female characters in the book, and none of them were Jazz's friends.",
				"/images/artemis.jpg", sf));
		Review eloquence = reviewRepo.save(new Review("The Elements of Eloquence: How to Turn the Perfect English Phrase",
				"From the chapter on Hyperbation (putting words in an odd order):\r\n"
						+ "\". . .adjectives in English absolutely have to be in this order: opinion-size-age-shape-colour-origin-material-purpose-Noun. So you can have a lovely little old rectangular green French silver whittling knife. But if you mess with that word order in the slightest you'll sound like a maniac. [. . . ] There are other rules that everybody obeys without noticing. Have you ever heard that patter-pitter of tiny feet? Or the dong-ding of a bell? Or hop-hip music? That's because, when you repeat a word with a different vowel, the order is always I A O. Bish bash bosh. So politicians may flip-flop, but they can never flop-flip.\"\r\n"
						+ "Mark Forsyth blows my mind for the thousandth time. ",
				"/images/eloquence.jpg", nonfiction));
		Review psychopathTest = reviewRepo.save(new Review(
				"The Psychopath Test: A Journey Through the Madness Industry",
				"Fascinating and humorous, eye-opening, and full of irony, this book follows Jon Ronson's exploration of madness. What is a psychopath? How can they be treated, if at all? Does the media pander to people who are just mad enough to be interesting, reassure us we aren't crazy, and don't make us pity them? I'm going to have to read his other books, too.",
				"/images/psychopathTest.jpg", nonfiction));
		Review furiouslyHappy = reviewRepo.save(new Review("Furiously Happy: A Funny Book About Horrible Things",
				"Sort of a hilarious philosophical guide to living your life, really living it, just to spite the depression and anxiety that plague you. It felt liberating and affirming to read.",
				"/images/happy.jpg", memoir, humor));
		Review martian = reviewRepo.save(new Review("The Martian",
				"Astronaut Mark Watney accidentally gets marooned on Mars when his crewmates believe he's dead in an emergency that forces them to abort their mission. Mark is resourceful, intelligent, well-trained by NASA, and possessed of a goofy sense of humor. He has to figure out how to survive, how to commuicate with Earth, and how to get back home. The details are astounding, and it really shows that the author knows the ins and outs of manned spaceflight.",
				"/images/martian.jpg", sf));

		Author cWright =new Author("Camron Wright", orphanKeeper);
		cWright =  authorRepo.save(cWright);
		Author jLawson =new Author("Jenny Lawson", letsPretend, furiouslyHappy);
		jLawson = authorRepo.save(jLawson);
		Author aFinn = new Author("A.J. Finn", womanWindow);
		aFinn = authorRepo.save(aFinn);
		Author mForsyth = new Author("Mark Forsyth", eloquence);
		mForsyth =  authorRepo.save(mForsyth);
		Author jRonson = new Author("Jon Ronson", psychopathTest);
		jRonson =  authorRepo.save(jRonson);
		Author mOrtberg = new Author("Mallory Ortberg", janeTexts);
		mOrtberg = authorRepo.save(mOrtberg);
		Author dPreston =new Author("Douglas Preston", monkeyGod);
		dPreston = authorRepo.save(dPreston);
		Author aWeir = new Author("Andy Weir", artemis, martian);
		aWeir =  authorRepo.save(aWeir);
	}

}
