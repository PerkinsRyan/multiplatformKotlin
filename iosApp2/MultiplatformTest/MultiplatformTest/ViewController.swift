import UIKit
import main
import Firebase

class ViewController: UITableViewController, MainView {
    
    @IBOutlet weak var searchBar: UISearchBar!
    
    let presenter = MainPresenter(parkRepository: ParkRepository())
    
    private var parks: [Park] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //presenter.bindView(mainView: self)
        searchBar.delegate = self
        
        let db = Firestore.firestore()
        
        db.collection("parks").addSnapshotListener { (snapshot, _) in
            var parks = snapshot?.documents.compactMap { document -> Park in
                let name = document.data()["name"] as? String
                let visited = document.data()["visited"] as? Bool
                return Park.init(name: name ?? "", visited: visited ?? false)
                } ?? []
            parks.sort { $0.name < $1.name }
            self.showParks(parks: parks)
        }
    }
    
    func showParks(parks: [Park]) {
        self.parks = parks
        tableView.reloadData()
    }
    
    func showVisitedCount(count: Int32) {
        title = "\(count) Parks Visited"
    }
}

extension ViewController: UISearchBarDelegate {
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        let query = searchBar.text!
        presenter.filterParks(filter: query)
    }
}

extension ViewController {
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return parks.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let park = parks[indexPath.row]
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        cell.textLabel?.text = park.name
        return cell
    }
}
