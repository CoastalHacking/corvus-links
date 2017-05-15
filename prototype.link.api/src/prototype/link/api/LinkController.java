package prototype.link.api;

public interface LinkController {

	void popup();
	
	void cancel();
	
	void start(LinkDTO linkDTO);
	
	void add(LinkDTO linkDTO);
	
	void end(LinkDTO linkDTO);

	void undo();
}
