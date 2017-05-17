package prototype.link.api;

public interface LinkController {

	void popup();

	void start(LinkDTO linkDTO);

	void end(LinkDTO linkDTO);
	
	void add(LinkDTO linkDTO);

	void cancel();
}
